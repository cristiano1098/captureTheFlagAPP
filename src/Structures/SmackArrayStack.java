
package Structures;

import Interfaces.SmackStackADT;
import Exceptions.EmptyCollectionException;


public class SmackArrayStack<T> extends ArrayStack<T> implements SmackStackADT<T> {

    public SmackArrayStack(){
        super();
    }

    @Override
    public T smack() throws EmptyCollectionException {
        if(isEmpty())throw new EmptyCollectionException("Stack vazia");
        
        T result = stack[0];
        top--;
        
        for(int i = 0; i < top; i++){
            stack[i] = stack[i+1];
        }
                
        return result;
    }
    
    
}
