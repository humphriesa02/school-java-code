package simulator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.Before;


public class BitStringTest {

   @Before
   public void setUp() throws Exception {
   }

   @Test
   public void testBitStringConstructor() {
      BitString bitString = new BitString();
      assertNotNull(bitString != null);
      assertEquals(bitString.getLength(), 0);
      assertArrayEquals(bitString.getBits(), null);
   }

   @Test
   public void testSetBitsOverLength() {
      BitString bitString = new BitString();
      try {
         bitString.setBits(new char[17]);
         fail("SetBits failed");
      } catch (IllegalArgumentException ie) {
      
      }
   }

   @Test
   public void testSetBits() {
      BitString bitString = new BitString();
      char test[] = { '1', '0', '1', '0' };
      bitString.setBits(test);
      assertEquals(bitString.getLength(), 4);
      assertArrayEquals(bitString.getBits(), test);
   }

   @Test
   public void testInvert() {
      char allOnes[] = { '1', '1', '1', '1' };
      char allZeros[] = { '0', '0', '0', '0' };
      BitString bitString = new BitString();
      bitString.setBits(allZeros);
      bitString.invert();
      assertArrayEquals(bitString.getBits(), allOnes);
      bitString.invert();
      assertArrayEquals(bitString.getBits(), allZeros);
   }

   @Test
   public void testAddOne() {
      char allZeros[] = { '0', '0', '0', '0' };
      char one[] = { '0', '0', '0', '1' };
      char two[] = { '0', '0', '1', '0' };
      char allOnes[] = { '1', '1', '1', '1' };
      BitString bitString = new BitString();
      bitString.setBits(allZeros);
      bitString.addOne();
      assertArrayEquals(bitString.getBits(), one);
      bitString.setBits(allOnes);
      bitString.addOne();
      assertArrayEquals(bitString.getBits(), allZeros);
      bitString.setBits(one);
      bitString.addOne();
      assertArrayEquals(bitString.getBits(), two);
   }

   @Test
   public void testSetValueInvalid() {
   
      BitString bitString = new BitString();
      try {
         bitString.setValue(-10);
         fail("Can set negative value for unsigned");
      } catch (IllegalArgumentException e) {
      
      }
      try {
         bitString.setValue(65536);
         fail("Can set more than max for unsigned");
      } catch (IllegalArgumentException e) {
      
      }
   
   }

   @Test
   public void testSetValue() {
      char ten[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
         	'0', '1', '0', '1', '0' };
   
      BitString bitString = new BitString();
      bitString.setValue(10);
      assertArrayEquals(bitString.getBits(), ten);
   
   }

   @Test
   public void testSetValue2sComp() {
      char max[] = { '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
         	'1', '1', '1', '1', '1' };
      char min[] = { '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
         	'0', '0', '0', '0', '0' };
      BitString bitString = new BitString();
      bitString.setValue2sComp(32767);
      assertArrayEquals(bitString.getBits(), max);
      bitString.setValue2sComp(-32768);
      assertArrayEquals(bitString.getBits(), min);
   }

   @Test
   public void testSetValue2sCompInvalid() {
      BitString bitString = new BitString();
      try {
         bitString.setValue2sComp(-32769);
         fail("Can set negative value for 2s comp");
      } catch (IllegalArgumentException e) {
      
      }
      try {
         bitString.setValue2sComp(32768);
         fail("Can set more than max for 2s comp");
      } catch (IllegalArgumentException e) {
      
      }
   }

   @Test
   public void testGetValue() {
      char ten[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
         	'0', '1', '0', '1', '0' };
      BitString bitString = new BitString();
      bitString.setBits(ten);
      assertEquals(bitString.getValue(), 10);
   
   }

   @Test
   public void testGetValue2sComp() {
      char ones[] = { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
         	'1', '1', '1', '1', '1' };
      char min[] = { '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
         	'0', '0', '0', '0', '0' };
      BitString bitString = new BitString();
      bitString.setBits(ones);
      assertEquals(bitString.getValue2sComp(), -1);
      bitString.setBits(min);
      assertEquals(bitString.getValue2sComp(), -32768);
   }

   @Test
   public void testAppend() {
      char fourBits[] = { '0', '0', '0', '0' };
      char eightBits[] = { '1', '0', '0', '0', '0', '0', '0', '0' };
      char twelveBits[] = { '0', '0', '0', '0', '1', '0', '0', '0', '0', '0',
         	'0', '0' };
      BitString bitString = new BitString();
      bitString.setBits(fourBits);
      bitString.display(true);
      BitString anotherBitString = new BitString();
      anotherBitString.setBits(eightBits);
      BitString appendedString = bitString.append(anotherBitString);
      appendedString.display(true);
      assertArrayEquals(appendedString.getBits(), twelveBits);
   }

   @Test
   public void testSubstring() {
      char twelveBits[] = { '0', '0', '0', '0', '1', '0', '0', '0', '0', '0',
         	'0', '0' };
      char eightBits[] = { '1', '0', '0', '0', '0', '0', '0', '0' };
      BitString bitString = new BitString();
      bitString.setBits(twelveBits);
      BitString partString = bitString.substring(4, 8);
      assertArrayEquals(partString.getBits(), eightBits);
   }

   @Test
   public void testAddInstruction(){
      // Create computer
      Computer comp = new Computer();
      // Create clear instruction to clear out R1
      BitString clear = new BitString();
      clear.setBits("0101100100100000".toCharArray());
      comp.loadWord(0,clear);
      // Create add instruction
      BitString add = new BitString();
      add.setBits("0001100100111101".toCharArray()); // adding -3 to R4
      comp.loadWord(1,add);
      // Halt instruction
      BitString halt = new BitString();
      halt.setBits("1111111111010000".toCharArray()); // halt instruction
      comp.loadWord(2,halt);
      // Result binary expression
      BitString result = new BitString();
      result.setBits("1111111111111101".toCharArray());
      comp.execute();
      System.out.println(result.getValue());
      System.out.println(result.getValue2sComp());
      assertEquals(result.getValue2sComp(), comp.getRegister(4).getValue2sComp());
      assertEquals(4, comp.getmCC().getValue());
   }

   @Test
   public void testAndInstruction(){
      // Create computer
      Computer comp = new Computer();
      // Use add to put some number in R4
      BitString add = new BitString();
      add.setBits("0001001001000101".toCharArray());
      comp.loadWord(0,add);
      // Use and to compare bits from R1 and the number 4
      BitString and = new BitString();
      and.setBits("0101001001100100".toCharArray());
      comp.loadWord(1,and);
      // Set our halt instruction to prevent overflow
      BitString halt = new BitString();
      halt.setBits("1111111111010000".toCharArray()); // halt instruction
      comp.loadWord(2,halt);
      // Our result from the and is all the bits that are shared from
      // R1 (6) and the number 4
      BitString result = new BitString();
      result.setBits("0000000000000100".toCharArray());
      comp.execute();
      // The outcome should be 4
      assertEquals(result.getValue2sComp(), comp.getRegister(1).getValue2sComp());

      assertEquals(1, comp.getmCC().getValue());
   }

   @Test
   public void testLdInstruction() {
      // Create computer
      Computer comp = new Computer();
      // set data into memory instruction
      BitString data = new BitString();
      data.setBits("0000000000000101".toCharArray()); // should be 5
      comp.loadWord(0,data); // Memory location 0 now holds 5
      // Use and to clear R2
      BitString clear = new BitString();
      clear.setBits("0101010010100000".toCharArray()); // clear R2
      comp.loadWord(1,clear); // Memory location 1 now holds the clear instruction
      // Load memory address 0 into R2
      BitString load = new BitString();
      load.setBits("0010010111111101".toCharArray()); // Pc offset set to -3 (pc will go back 3)
      comp.loadWord(2,load);
      // Set our halt instruction to prevent overflow
      BitString halt = new BitString();
      halt.setBits("1111111111010000".toCharArray()); // halt instruction
      comp.loadWord(3, halt);
      // Result will be just the data we inserted into memory location 1
      // But will be present in register 2
      BitString result = new BitString();
      result.setBits("0000000000000101".toCharArray());
      comp.execute();
      System.out.println(comp.getMemory(0).getValue2sComp());
      assertEquals(result.getValue2sComp(), comp.getRegister(2).getValue2sComp());

      assertEquals(1, comp.getmCC().getValue());
   }
   @Test
   public void testBrInstruction(){
// Create computer
      Computer comp = new Computer();
      // Use add to put some number in R0
      BitString zero = new BitString();
      zero.setBits("0001000000000001".toCharArray()); // Sets 1 to R0
      comp.loadWord(0,zero);
      // Branch check
      BitString br = new BitString();
      br.setBits("0000001111111110".toCharArray());
      comp.loadWord(1,br);
      // if we see that the pc offset -1 is positive, continue
      BitString out = new BitString();
      out.setBits("1111000000100001".toCharArray());
      comp.loadWord(2,out);
      // Set our halt instruction to prevent overflow
      BitString halt = new BitString();
      halt.setBits("1111111111010000".toCharArray()); // halt instruction
      comp.loadWord(3,halt);
      comp.execute();
   }
}

