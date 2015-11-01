
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Encryptor  {
	
	private static final String[] alphabetArray = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	private static final ArrayList<String> alphabet = new ArrayList<String>(Arrays.asList(alphabetArray));
	

	public static void main(String[] args) {

		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> chars = new ArrayList<String>();
		ArrayList<String> encrypted = new ArrayList<String>();
		ArrayList<Integer> decrypted = new ArrayList<Integer>();
		ArrayList<String> decryptedToLetters = new ArrayList<String>();
		
		String line = JOptionPane.showInputDialog("Type a something to be encrypted.", null);
		
		
		
		encrypted = encrypt(line);
		//System.out.println(encrypted);
		
		decrypted = decrypt(encrypted);
		//System.out.println(decrypted);
		
		decryptedToLetters = decryptedToString(decrypted);
		//System.out.println(decryptedToLetters);
		
		
		JOptionPane.showMessageDialog(null,"Encrypted Message: " + encrypted + "\r\n \r\n" + "Decrypted Message: " + decrypted + "\r\n \r\n" + "Decrypted To Letters: " + decryptedToLetters);
		
		
		
	}
	
	public static ArrayList<String> encrypt(String line){
		ArrayList<String> output = new ArrayList<String>();
		ArrayList<String> chars = new ArrayList<String>();
		
		line.trim();
		
		for(int i = 1; i <= line.length(); i++){
			if(!line.substring(i-1,i).equals(" ")){
				chars.add(line.substring(i-1,i));
			}
			
		}
		
		for(int i = 0; i < chars.size(); i++){
			int num = 0;
			String adds = "";
			
			//num equals the num of the alphabet chars.get(i) is
			for(int k = 0; k < alphabet.size(); k++){
				if(chars.get(i).equalsIgnoreCase(alphabet.get(k))){
					num = k + 1;
					break;
				}
			}
			
			//First Operation: takes num, checks if dividing it by 13 equals 1, if yes, add A to to the addons so the decryption knows how to properly decrpyt it. And set num equal to num % 13
			//if num divided by 13 doesn't equal 1, just set num equal to num % 13
			if(num / 13 == 1){
				num = num % 13;
				adds += "A";
			}else if(num / 13 == 2){
				num = num % 13;
				adds += "C";
			}else {
			
				num = num % 13;
			}
			
			
			//Second Operation: takes num, checks if its an odd number, if yes, add B to the addons so the decryption knows how to properly decrpyt it. And set num equal to num / 2
			//if num isnt odd, just set num equal to num / 2
			if(num % 2 == 1){
				num /= 2;
				adds += "B";
			}else {
				num /= 2;
			}
			
			//Third Operation: times num by its place in the word 
			num *= (i+1);
			
			String str = num + adds;
			output.add(str);
		}
		
		
		
		return output;
	}
	
	
	
	public static ArrayList<Integer> decrypt(ArrayList<String> input){
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		for(int i = 0; i < input.size(); i++){
			String current = input.get(i);
			String adds = "";
			String strnum = "";
			Scanner scan = new Scanner(current);
			scan.useDelimiter("");
			
			while(scan.hasNextInt()){
				strnum += scan.nextInt();
			}
			
			int num = Integer.parseInt(strnum);
			
			while(scan.hasNext()){
				adds += scan.next();
			}
			
			
			
			//First Operation: divide num by its place in the word
			num /= (i + 1);
			
			
			//Second Operation: if adds has a B in it, we must add 1 to num * 2 because of previous integer division
			//if it doesn't, just set num equal to num * 2
			if(adds.contains("B")){
				num = (num * 2) + 1;
			}else {
				num *= 2;
			}
			
			//Third Operation: if adds contains an A, we must add 13 because of previous modular division
			//if it doesn't, dont do anything to num
			if(adds.contains("A")){
				num += 13;
			}else if(adds.contains("C")){
				num += 26;
			}
			
			output.add(num);
		}
		
		
		
		return output;
	}
	
	
	public static ArrayList<String> decryptedToString(ArrayList<Integer> input){
		ArrayList<String> output = new ArrayList<String>();
		
		for(int i = 0; i < input.size(); i++){
			int current = input.get(i);
			
			output.add(alphabet.get(current - 1));
		}
		
		return output;
	}
