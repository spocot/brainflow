package com.github.spocot.flow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);

		StringBuilder sb = new StringBuilder("");
		File f = new File("programs/testProgram.flo");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line = null;
		while((line = br.readLine()) != null){
			sb.append(line);
		}
		br.close();
		System.out.println("Length: " + sb.length());
		Interpreter interp = new Interpreter(sb.toString(), in);
		interp.run();

		in.close();
	}
}
