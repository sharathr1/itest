package com.ip.itest.java;

import java.util.ArrayList;

public class ArrayListAnySize<E> extends ArrayList<E>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void add(int index, E element){
        if(index >= 0 && index <= size()){
            super.add(index, element);
            return;
        }
        int insertNulls = index - size();
        for(int i = 0; i < insertNulls; i++){
            super.add(null);
        }
        super.add(element);
    }
	
	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		if(index>=0 && index <=size()){
		return super.set(index, element);
		}
		else{
			 int insertNulls = index - size();
		        for(int i = 0; i <= insertNulls; i++){
		            super.add(null);
		        }
			   return super.set(index, element);  
		}
	}

	@Override
	public E get(int index) {
		
	        int insertNulls = index - size();
	        for(int i = 0; i <= insertNulls; i++){
	            super.add(null);
	        }
		return super.get(index);
	}
	

	
}