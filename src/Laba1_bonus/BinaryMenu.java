package Laba1_bonus;

import java.io.*;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class BinaryMenu {
	private static Scanner reader = new Scanner(System.in);

	private static boolean Menu(DataInput in, DataOutput out, RandomAccessFile file, int vib) {
		Tour t = new Tour();
		int number;
		switch (vib) {
		case 0:
			System.out.println("��� ������ ������ ������� ��� ��������");
			System.out.println("1 - �������� ���������� � �����");
			System.out.println("2 - ���������� ���� � �����");
			System.out.println("3 - �������� ���� � �������� �������");
			System.out.println("4 - �������������� ���� � �������� �������");
			System.out.println("666 - ����� + ���������� � ����");
			break;
		case 1:
			System.out.println("�������� ���������� � �����");
			t.PrintAll(in);
			break;
		case 2:
			System.out.println("���������� ���� � �����");
			try {
				t.AddTourInEndFile(out, in);
			} catch (Exception e) {
				System.out.println("�� ������� �������� � ����");
			}
			break;
		case 3:
			System.out.println("�������� ���� � �������� �������");
			System.out.println("������� ����� ���������� ����");
			number = reader.nextInt();
			try {
				t.DeleteTour(in, out, file, number);
			} catch (Exception e) {
				System.out.println("�� ������� ������� ������, ��� ������ ������ ���");
			}
			break;
		case 4:
			System.out.println("�������������� ���� � �������� �������");
			System.out.println("������� ����� ����������� ����");
			number = reader.nextInt();
            try{
            	t.makeModeInTour(file, file, file, number);
            }catch(Exception e){
            	System.out.println("�� �������� ��������������");
            }
			break;
		case 999:
			System.out.println("������� �����");
			number = reader.nextInt();
			t.writeRandomTourToFile(number, in, out);
		case 666:
			System.out.println("�������� �����");
			if (vib == 666)
				return false;
			break;
		}
		return true;

	}

	public static void main(String[] args) {
		RandomAccessFile file = null;
		boolean exit = true;
		try {
			file = new RandomAccessFile("D:/file/tourFile/BaseTourFile.txt", "rw");
		} catch (IOException e) {
			System.out.println("������ �������� �����, ��� ������ ����� ��� � �.�.");
			exit = false;
		}

		System.out.println("���� �� ������, ��� ������ ������ ������ ������� '0'");
		while (exit) {
			System.out.println("������� ����� ������");
			try {
				file.seek(0);
			} catch (IOException e) {
				System.out.println("��� ������������ 2, �� �������� ��������");
			}
			int vib = reader.nextInt();
			exit = Menu(file, file, file, vib);
			System.out.println();
		}
		reader.close();
	}

}
