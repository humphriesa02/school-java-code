/**
 * Alex Humphries
 * TCSS 342
 * Polynomial ADT
 * Homework #3
 * Professor Donald Chinn
 */

package humphries.hwthree;

import java.util.NoSuchElementException;
/** Polynomial ADT
 *
 * @author TCSS 342, Alex Humphries
 * @version 1.0
 */
public class Polynomial {
    // Fields
    private LinkedList terms;

    // Constructors
    public Polynomial() {
        //Populate list
        terms = new LinkedList();
    }

    // Methods
    /**
     * Check base case then assign two iterators,
     * one for current and one for previous.
     * If the current literal has a smaller exp.
     * Than the one we're inserting, insert
     * new literal at previous.
     * @param coeficient base of the polynomial
     * @param exponent exponent that the base is being raised to.
     */
    public void insertTerm(int coeficient, int exponent){
        if(coeficient == 0)
            return;
        if(terms.isEmpty()){
            terms.insert(new Literal(coeficient,exponent), terms.zeroth());
        }else{
            LinkedList.Iterator current = terms.iterator();
            LinkedList.Iterator previous = terms.zeroth();
            while(current.hasNext()){
                Literal currentElement = (Literal) current.getElement();
                //Starting at the very beginning, check if the next
                //Node's element has a smaller exponent, if it does
                //Insert the literal here.
                if(currentElement.getExponent() < exponent){
                    terms.insert(new Literal(coeficient,exponent), previous);
                    break;
                }else if(currentElement.getExponent() == exponent){
                    currentElement.setCoefficient(currentElement.getCoefficient() + coeficient);
                    break;
                }
                //Iterate
                current.next();
                previous.next();
            }
            //If we got through the whole loop without stopping,
            //We're at the end, and should insert the term now.
            if(!current.hasNext()){
                terms.insert(new Literal(coeficient,exponent), previous);
            }

        }
    }

    /**
     * Set the linked list to empty.
     */
    public void zeroPolynomial() { terms.makeEmpty();}

    /**
     * Check if empty and throw error
     * If not, set iterator to head
     * while it has nodes set each
     * of the literals to the negative of
     * their values.
     * @return this negated polynomial
     */
    public Polynomial negate() {
        if(terms.isEmpty())
            throw new NoSuchElementException("No element");
        else{
            LinkedList.Iterator it = terms.iterator();
            while(it.hasNext()){
                Literal tempLit = (Literal) it.getElement();
                it.getNode().setElement(new Literal(-tempLit.getCoefficient(), tempLit.getExponent()));
                it.next();
            }
        }
        return this;
    }

    /**
     * We take 3 iterators, two for current and
     * previous of the original polynomial, and one
     * for the current of the new polynomial
     * For each value in it2, we loop through the entirety
     * of the original polynomial, if no exponents match
     * between the two polynomials it gets inserted,
     * else it gets added.
     * @param polynomial Polynomial for adding
     * @return sum of two polynomials
     */
    public Polynomial plus(Polynomial polynomial) {
        LinkedList.Iterator current = terms.iterator();
        LinkedList.Iterator previous = terms.zeroth();
        LinkedList.Iterator it2 = polynomial.terms.iterator();
        while(it2.hasNext()){
            Literal termTwo = (Literal)it2.getElement();
            while(current.hasNext()){
                Literal termOne = (Literal) current.getElement();
                if(termOne.getCoefficient() + termTwo.getCoefficient() == 0){
                    terms.remove(previous);
                }
                else if(termOne.getExponent() == termTwo.getExponent()){
                    current.getNode().setElement(new Literal(termOne.getCoefficient()+termTwo.getCoefficient(), termOne.getExponent()));
                    break;
                }
                else if(termOne.getExponent() < termTwo.getExponent()){
                    terms.insert(termTwo, previous);
                    break;
                }
                    //Iterate
                current.next();
                previous.next();
            }
            it2.next();
        }
        return this;
    }
    /**
     * We take 3 iterators, two for current and
     * previous of the original polynomial, and one
     * for the current of the new polynomial
     * For each value in it2, we loop through the entirety
     * of the original polynomial, if no exponents match
     * between the two polynomials it gets inserted,
     * else it gets subtracted.
     * When we insert here we flip the coefficient.
     * @param polynomial Polynomial for adding
     * @return sum of two polynomials
     */
    public Polynomial minus(Polynomial polynomial) {
        LinkedList.Iterator current = terms.iterator();
        LinkedList.Iterator previous = terms.zeroth();
        LinkedList.Iterator it2 = polynomial.terms.iterator();
        while(it2.hasNext()){
            Literal termTwo = (Literal)it2.getElement();
            while(current.hasNext()){
                Literal termOne = (Literal) current.getElement();
                //Check for 0 coefficients
                if(termOne.getCoefficient() - termTwo.getCoefficient() == 0){
                    terms.remove(previous);
                }
                if(termOne.getExponent() == termTwo.getExponent()){
                    current.getNode().setElement(new Literal(termOne.getCoefficient()-termTwo.getCoefficient(), termOne.getExponent()));
                    break;
                }
                else if(termOne.getExponent() < termTwo.getExponent()){
                    terms.insert(new Literal(-termTwo.getCoefficient(), termTwo.getExponent()), previous);
                    break;
                }
                //Iterate
                current.next();
                previous.next();
            }
            it2.next();
        }
        return this;
    }

    //No time
    public Polynomial times(Polynomial polynomial) { return null;}	// BONUS method

    /**
     * 4 variables - a new polynomial, it's terms
     * An iterator for the original terms at the head,
     * and an iterator for the new polynomial terms at the zeroth
     * We loop through and do a special case for a 0 exponent
     * else we do the derivative of the element in the original
     * polynomial and insert it into the derTerms.
     * @return
     */
    public Polynomial derivative() {
        Polynomial der = new Polynomial();
        LinkedList derTerms = der.terms;
        LinkedList.Iterator current = terms.iterator();
        LinkedList.Iterator previous = derTerms.zeroth();
        while (current.hasNext()) {
            if (current.getNode().getElement() != null) {
                Literal literal = (Literal) current.getNode().getElement();
                if (literal.getExponent() == 0) {
                    Literal newLit = new Literal(0, literal.getExponent());
                    derTerms.insert(newLit,previous);
                } else {
                    int newCoef = literal.getCoefficient() * literal.getExponent();
                    int newExp = literal.getExponent() - 1;
                    Literal newLit = new Literal(newCoef, newExp);
                    derTerms.insert(newLit, previous);
                }
            }
            current.next();
            previous.next();
        }
        return der;
    }

    /**
     * Through a series of checks
     * We determine how each literal of
     * the polynomial gets represented on print.
     * @return Printed Polynomial
     */
    public String print() {
        if(terms.isEmpty()) {
            return "0";
        }
        LinkedList.Iterator current = terms.iterator();
        StringBuffer polyString = new StringBuffer();
        boolean firstPrint = false;

        while(current.hasNext()) {
            Literal lit = (Literal) current.next();
            if(lit.getCoefficient() < 0) {
                polyString.append(" - " + Math.abs(lit.getCoefficient()));
            } else {
                if(firstPrint) {
                    polyString.append(" + ");
                }
                // When we have a 0 exponent
                if(lit.getCoefficient() == 1 && lit.getExponent() == 0) {
                    polyString.append(1);
                    continue;
                }
                if(lit.getCoefficient() != 1) {
                    polyString.append(lit.getCoefficient());
                }
            }
            if(lit.getExponent() != 0) {
                //No exponent
                if(lit.getExponent() == 1) {
                    polyString.append("x");
                } else {
                    polyString.append("x^");
                    if(lit.getExponent() < 0) {
                        polyString.append("(");
                        polyString.append(lit.getExponent());
                        polyString.append(")");
                    } else {
                        polyString.append(lit.getExponent());
                    }
                }
            }
            // At the end of the first print let us know
            // that we can print + signs
            firstPrint = true;
        }
        return polyString.toString();
    }

}
