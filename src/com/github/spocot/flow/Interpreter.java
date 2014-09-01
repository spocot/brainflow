package com.github.spocot.flow;

import java.util.Scanner;

public class Interpreter {

	private String exp;

	private int[] values = new int[256];
	private int index = 0;
	
	private Scanner in;

	public Interpreter(String exp, Scanner in){
		this.exp = exp;
		this.in = in;
	}

	public void run(){
		//Reset index and values
		for(int i = 0; i < values.length; i++){
			values[i] = 0;
		}
		this.index = 0;

		System.out.println("Starting...");
		this.process(this.exp, false);
		System.out.println("\nDone.");
	}

	private void process(String str, boolean loop){
		boolean running = loop;
		do{
			for(int i = 0; i < str.length(); i++){
				switch(str.charAt(i)){
				case '>':increaseIndex();break;
				case '<':decreaseIndex();break;
				case '+':increaseValue();break;
				case '-':decreaseValue();break;
				case '[':
					String s = str.substring(i);
					int j = this.getClosingIndex(s);
					if(this.values[this.index] == 0){
						i +=j;
						break;
					}
					process(s.substring(1, j), true);
					i += j;
					break;
				case '.':
					int v = this.values[this.index];
					System.out.print((char)v);
					break;
				case ',':this.values[this.index] = this.in.next().charAt(0);System.out.println("ValRead:[" + this.values[this.index] + "]");break;
				case '^':this.index = this.values[this.index];break;// Jumps to the index specified in the current cell.
				case '=':this.values[index] = this.index;break;// Sets the value at cell #x to x
				case '&':this.values[index] = this.values[this.values[index]];break;// If cell contains X, makes value of current cell equal to value in cell X
				case '@':System.out.print("[:" + this.index + ":]");
				default:
					//Ignore others
					break;
				}
			}
			if(this.values[this.index] == 0){
				running = false;
			}
		}while(running);
	}

	private void increaseIndex(){
		if(++this.index >= this.values.length){
			this.index = 0;
		}
	}

	private void decreaseIndex(){
		if(--this.index < 0){
			this.index = this.values.length - 1;
		}
	}

	private void increaseValue(){
		int newVal = this.values[this.index] + 1;
		if(newVal >= this.values.length){
			newVal = 0;
		}
		this.values[this.index] =  newVal;
	}

	private void decreaseValue(){
		int newVal = this.values[this.index] - 1;
		if(newVal < 0){
			newVal = this.values.length - 1;
		}
		this.values[this.index] =  newVal;
	}
	
	private int getClosingIndex(String str){
		int openings = 0;
		int closings = 0;
		for(int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if(c == '['){
				openings++;
			}else if(c == ']'){
				closings++;
			}
			if(openings == closings){
				return i;
			}
		}
		return -1;
	}
}
