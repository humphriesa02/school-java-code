package simulator;

/**
 * Computer class comprises of memory, registers, cc and
 * can execute the instructions based on PC and IR 
 * @author mmuppa
 *
 */
public class Computer {

   private final static int MAX_MEMORY = 50;
   private final static int MAX_REGISTERS = 8;

   private BitString mRegisters[];
   private BitString mMemory[];
   private BitString mPC;
   private BitString mIR;
   private BitString mCC;

	/**
	 * Initializes all the memory to 0, registers to 0 to 7
	 * PC, IR to 16 bit 0s and CC to 000 
	 * Represents the initial state 
	 */
   public Computer() {
      mPC = new BitString();
      mPC.setValue(0);
      mIR = new BitString();
      mIR.setValue(0);
      mCC = new BitString();
      mCC.setBits(new char[] { '0', '0', '0' });
      mRegisters = new BitString[MAX_REGISTERS];
      for (int i = 0; i < MAX_REGISTERS; i++) {
         mRegisters[i] = new BitString();
         mRegisters[i].setValue(i);
      }
   
      mMemory = new BitString[MAX_MEMORY];
      for (int i = 0; i < MAX_MEMORY; i++) {
         mMemory[i] = new BitString();
         mMemory[i].setValue(0);
      }
   }

   public BitString getRegister(int register){
      return mRegisters[register];
   }

   public BitString getMemory(int memory){return mMemory[memory];}
	/**
	 * Loads a 16 bit word into memory at the given address. 
	 * @param address memory address
	 * @param word data or instruction or address to be loaded into memory
	 */
   public void loadWord(int address, BitString word) {
      if (address < 0 || address >= MAX_MEMORY) {
         throw new IllegalArgumentException("Invalid address");
      }
      mMemory[address] = word;
   }

	 /**
	 * Performs not operation by using the data from the register based on bits[7:9] 
	 * and inverting and storing in the register based on bits[4:6]
	 */
   public void executeNot() {
      BitString destBS = mIR.substring(4, 3);
      BitString sourceBS = mIR.substring(7, 3);
      mRegisters[destBS.getValue()] = mRegisters[sourceBS.getValue()].copy();
      mRegisters[destBS.getValue()].invert();
      
      // set CC
      setmCC(destBS.getValue());
   }

   /**
    * Checks the condition code stored in bit 10
    * If it's 0, then it's adding from a different register
    * if it's a 1, then it's adding from a value
    */
   public void executeAdd(){
      // Conditional check to see if imm5 or register addition
      BitString condition = mIR.substring(10, 1);
      // destination register instruction
      BitString destBS = mIR.substring(4, 3);
      // source register instruction
      BitString sourceBSOne = mIR.substring(7,3);
      // Adding from source register
      if(condition.getValue() == 0){
         BitString sourceBSTwo = mIR.substring(13,3);
         int sourceBSOneValue = sourceBSOne.getValue();
         int sourceBSTwoValue = sourceBSTwo.getValue();
         int destBSNewValue = mRegisters[sourceBSOneValue].getValue2sComp() + mRegisters[sourceBSTwoValue].getValue2sComp();
         // Set ConditionCode
         setmCC(destBSNewValue);
         mRegisters[destBS.getValue()].setValue2sComp(destBSNewValue);
      }
      // Adding from imm5
      else if(condition.getValue() == 1){
         BitString imm5 = mIR.substring(11,5);
         int imm5Value = imm5.getValue2sComp();
         int destBSNewValue = mRegisters[sourceBSOne.getValue()].getValue2sComp() + imm5Value;
         setmCC(destBSNewValue);
         mRegisters[destBS.getValue()].setValue2sComp(destBSNewValue);
      }
   }

   /**
    * Similar to ADD but uses bitwise &
    */
   public void executeAnd(){
      // Conditional check to see if imm5 or register and
      BitString condition = mIR.substring(10, 1);
      // destination register instruction
      BitString destBS = mIR.substring(4, 3);
      // source register instruction
      BitString sourceBSOne = mIR.substring(7,3);
      // Adding from source register
      if(condition.getValue() == 0){
         BitString sourceBSTwo = mIR.substring(13,3);
         int sourceBSOneValue = sourceBSOne.getValue();
         int sourceBSTwoValue = sourceBSTwo.getValue();
         int destBSNewValue = mRegisters[sourceBSOneValue].getValue2sComp() & mRegisters[sourceBSTwoValue].getValue2sComp();
         // Set ConditionCode
         setmCC(destBSNewValue);
         mRegisters[destBS.getValue()].setValue2sComp(destBSNewValue);
      }
      // And from imm5
      else if(condition.getValue() == 1){
         BitString imm5 = mIR.substring(11,5);
         int destBSNewValue = mRegisters[sourceBSOne.getValue()].getValue2sComp() & imm5.getValue2sComp();
         setmCC(destBSNewValue);
         mRegisters[destBS.getValue()].setValue2sComp(destBSNewValue);
      }
   }

   /**
    * LD function [0010] [Destination Register] [ PC Offset 9]
    * Loads the instruction into destination register from the
    * Location that we find by adding the pc offset to our pc location
    */
   public void executeLd(){
      // Getting assigning variables by parsing instruction
      BitString destBS = mIR.substring(4, 3);
      BitString pcOffset = mIR.substring(7, 9);
      int pcOffsetInt = pcOffset.getValue2sComp();
      int currPc = mPC.getValue();
      int totalMovement = pcOffsetInt + currPc;
      mRegisters[destBS.getValue()] = mMemory[totalMovement];
      setmCC(destBS.getValue());
   }

   /**
    * BR function [0000][n][z][p][PCoffset9]
    * We check to see if our condition code is positive, negative, or 0,
    * depending on the outcome we either continue or return to a PC position
    */
   public void executeBr(){
      // if nzp == 4, check if mcc is negative, if it is continue
      // if nzp == 2, check if mcc is 0, if it is continue
      // if nzp == 1, check if mcc is positive, if it is continue

      BitString nzp = mIR.substring(4,3);
      BitString pcOffset = mIR.substring(7, 9);
      // Negative cc check
      if(nzp.getValue() == 4 && mCC.getValue() == -1){
         mPC.setValue(mPC.getValue() + pcOffset.getValue2sComp());
      }
      // 0 cc check
      else if(nzp.getValue() == 2 && mCC.getValue() == 0){
         mPC.setValue(mPC.getValue() + pcOffset.getValue2sComp());
      }
      // positive cc check
      else if(nzp.getValue() == 1 && mCC.getValue() == 1){
         mPC.setValue(mPC.getValue() + pcOffset.getValue2sComp());
      }
      // nzp case
      else if(nzp.getValue() == 7){
         mPC.setValue(mPC.getValue() + pcOffset.getValue2sComp());
      }

   }

   /**
    * gets the value inside R0, then
    * converts it to char and prints
    */
   public void executeOut(){
      BitString registerZero = mRegisters[0];
      BitString character = registerZero.substring(7,9);
      int charAscii = character.getValue2sComp();
      char newChar = (char) charAscii;
      System.out.println(newChar);
   }


	/**
	 * This method will execute all the instructions starting at address 0 
	 * till HALT instruction is encountered. 
	 */
   public void execute() {
      BitString opCodeStr;
      int opCode;

   
      while (true) {
      	// Fetch the instruction
         mIR = mMemory[mPC.getValue()];
         mPC.addOne();
      
      	// Decode the instruction's first 4 bits 
      	// to figure out the opcode
         opCodeStr = mIR.substring(0, 4);
         opCode = opCodeStr.getValue();
      
      	// What instruction is this?
         if (opCode == 9) { // NOT
            executeNot();
         }
         if(opCode == 1){ // ADD
            executeAdd();
         }
         if(opCode == 5){ // AND
            executeAnd();
         }
         if(opCode == 2){ // LD
            executeLd();
         }
         if(opCode == 0){ // BR
            executeBr();
         }
         if(opCode == 15){ // TRAP - OUT and HALT
            BitString trapVect = mIR.substring(8, 8);
            int trapVal = trapVect.getValue2sComp();
             //OUT = x21
            if(trapVal == 33)
               executeOut();
             //HALT
            if(trapVal == -48){
               return;
            }
         }
      }
   }

	/**
	 * Displays the computer's state
	 */
   public void display() {
      System.out.print("\nPC ");
      mPC.display(true);
      System.out.print("   ");
   
      System.out.print("IR ");
      mPC.display(true);
      System.out.print("   ");
   
      System.out.print("CC ");
      mCC.display(true);
      System.out.println("   ");
   
      for (int i = 0; i < MAX_REGISTERS; i++) {
         System.out.printf("R%d ", i);
         mRegisters[i].display(true);
         if (i % 3 == 2) {
            System.out.println();
         } else {
            System.out.print("   ");
         }
      }
      System.out.println();
   
      for (int i = 0; i < MAX_MEMORY; i++) {
         System.out.printf("%3d ", i);
         mMemory[i].display(true);
         if (i % 3 == 2) {
            System.out.println();
         } else {
            System.out.print("   ");
         }
      }
      System.out.println();
   
   }

   void setmCC(int result){
      if(result == 0)
         mCC.setBits(new char[]{'0','0','0'});
      else if(result > 0)
         mCC.setBits(new char[]{'0','0','1'});
      else if(result < 0)
         mCC.setBits(new char[]{'1','0','0'});
   }

   public BitString getmCC(){
      return mCC;
   }
}
