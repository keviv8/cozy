package TicketGen;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class ticketGen {

	public static void main(String[] args) throws AWTException, IOException, InterruptedException {


System.out.println(Instant.now());
		ArrayList<Integer> mapCoordinates = new ArrayList<Integer>();
		ArrayList<Integer> mapValues = new ArrayList<Integer>();
		ticketGen tG = new ticketGen();

		System.out.println("How many tickets?");
		Scanner in = new Scanner(System.in);
		int tcount = in.nextInt();
		Instant start = Instant.now();

//		Scanner s = new Scanner(System.in);
//		int a, b, count = 0;
//		a = s.nextInt();
//		b = s.nextInt();
//		if (a > b)
//			for (int i = 1; i <= a; i++)
//				if (a % i == 0)
//					count++;
//		if (b > a)
//			for (int i = 1; i <= b; i++)
//				if (b % i == 0)
//					count++;

		if (tcount == 1011) {
			int i = 0, j = 0;
			Robot hal = new Robot();
			Random random = new Random();
			while (true) {
				// Runtime.getRuntime().exec("notepad.exe");
				// Thread.sleep(2000);
				// Robot r = new Robot();
				// r.keyPress(KeyEvent.VK_T);
				// Thread.sleep(500);
				// r.keyPress(KeyEvent.VK_H);
				// Thread.sleep(500);
				// r.keyPress(KeyEvent.VK_I);
				// Thread.sleep(500);
				// r.keyPress(KeyEvent.VK_S);
				// Thread.sleep(500);
				// r.keyPress(KeyEvent.VK_SPACE);
				// Thread.sleep(500);
				// r.keyPress(KeyEvent.VK_I);
				// Thread.sleep(500);
				// r.keyPress(KeyEvent.VK_S);
				// Thread.sleep(500);
				// r.keyPress(KeyEvent.VK_SPACE);
				// Thread.sleep(500);
				// r.keyPress(KeyEvent.VK_F);
				// Thread.sleep(500);
				// r.keyPress(KeyEvent.VK_U);
				// Thread.sleep(500);
				// r.keyPress(KeyEvent.VK_N);
				hal.delay(1000 * 5);
				int x = random.nextInt() % 640;
				int y = random.nextInt() % 480;
				hal.mouseMove(i, j);
				i++;
				j++;

			}
		} else
			for (int t = 0; t < tcount; t++) {
				boolean mapRight = false;
				for (int i = 0; !mapRight; i++) {
					 mapCoordinates = tG.mapMaker();
					if (mapCoordinates.subList(27, mapCoordinates.size()).contains(0)) {
						mapRight = false;
						// System.out.println("Map Co-ordinates BAD");
						continue;
					} else {
						// System.out.println("Map co-ordinates are :\n\t\t\t" +
						// mapCoordinates.subList(0, 9) + "\n\t\t\t"
						// + mapCoordinates.subList(9, 18) + "\n\t\t\t" + mapCoordinates.subList(18, 27)
						// + "\nAnd the sum array is:\n\t\t\t" + mapCoordinates.subList(27,
						// mapCoordinates.size()));
						break;
					}
				}
				 mapValues = tG.mapGenerator(mapCoordinates);
				 tG.mapFiller(mapCoordinates, mapValues);
				 
				System.out.println("");//// to present
				System.out.println("_____________________________________________________________________");
				///// to present
			}
		Instant end = Instant.now();
		System.out.println("Time taken for " + tcount + " tickets generation = "
				+ (float) ((float) (Duration.between(start, end).toMillis()) / (float) 1000) + " seconds");
	}

	private static ArrayList<Integer> mapMaker() {


		ArrayList<Integer> r1f = new ArrayList<Integer>();
		ArrayList<Integer> r2f = new ArrayList<Integer>();
		ArrayList<Integer> r3f = new ArrayList<Integer>();
		ArrayList<Integer> sumarray = new ArrayList<Integer>();
		ArrayList<Integer> returnval = new ArrayList<Integer>();
		boolean r1 = false, r2 = false, r3 = false, all3 = false, rule1 = false, zerothere = false, mappingdone = true;
		int sum, i, sumall = 0;
		int s1, s2, s3;
		for (int z = 0; !zerothere && mappingdone; z++) {
			for (int k = 0; sumall != 15; k++) {
				for (i = 0; !all3 && !rule1; i++) {
					sum = 0;
					s1 = 0;
					s2 = 0;
					s3 = 0;
					ArrayList<Integer> row1 = new ArrayList<Integer>();
					ArrayList<Integer> row2 = new ArrayList<Integer>();
					ArrayList<Integer> row3 = new ArrayList<Integer>();

					for (int j = 0; j < 9; j++) {
						int n = (int) Math.floor(Math.random() * 2);
//						 System.out.print(n+"\t");
						row1.add(n);
					}
					for (Integer d : row1)
						s1 += d;
					if (s1 == 5) {
//						 System.out.println("Row 1 is\t" + row1 + "\t" + sum);
						r1 = true;
						s1 = 0;
						r1f = row1;
					}

					for (int j = 0; j < 9; j++) {
						int n = (int) Math.floor(Math.random() * 2);
//						 System.out.print(n+"\t");
						row2.add(n);
					}
					for (Integer d : row2)
						s2 += d;
					if (s2 == 5) {
//						 System.out.println("Row 2 is\t" + row2 + "\t" + sum);
						r2 = true;
						s2 = 0;
						r2f = row2;
					}

					for (int j = 0; j < 9; j++) {
						int n = (int) Math.floor(Math.random() * 2);
//						 System.out.print(n+"\t");
						row3.add(n);
					}
					for (Integer d : row3)
						s3 += d;
					if (s3 == 5) {
//						 System.out.println("Row 3 is\t" + row3 + "\t" + sum);
						r3 = true;
						s3 = 0;
						r3f = row3;
					}

					if (r1 == true && r2 == true && r3 == true)
						all3 = true;

					if (all3 == true) {
						for (Integer f : r1f)
							sumall += f;
						for (Integer f : r2f)
							sumall += f;
						for (Integer f : r3f)
							sumall += f;
						if (sumall == 15) {
							for (int r = 0, rc = 0; r < 9; r++) {
//								 System.out.println(r1f + "\n" + r2f + "\n" + r3f);
								rc = r1f.get(r) + r2f.get(r) + r3f.get(r);
								sumarray.add(rc);
								if (rc == 0) {
									// rule1 = true;
									// all3 = false;
								}
							}
						}

						if (sumall != 15) {
							// System.out.println("Made a mistake");
//							 System.out.println(r1f + "\n" + r2f + "\n" + r3f);
							all3 = false;
							sum = 0;
							break;
						}
					}
				}
				// if (all3 = true && sumall != 15) {
				// System.out.println("Made a mistake");
//				 System.out.println(r1f + "\n" + r2f + "\n" + r3f+"\t"+sumall);
				// all3 = false;
				// continue;
				// }
				// System.out.println("Completed in " + i + " tries");
			}
			for (Integer f : sumarray) {
				if (f == 0) {
					// System.out.println("Zero is at " + sumarray.indexOf(f));
					zerothere = true;
				}
			}
			for (Integer f : sumarray) {
				if (f != 0) {
					mappingdone = false;
					continue;
				}
			}
//			 System.out.println(r1f + "\n" + r2f + "\n" + r3f + "\t" + sumarray);
		}
		returnval.addAll(r1f);
		returnval.addAll(r2f);
		returnval.addAll(r3f);
		returnval.addAll(sumarray);
		return returnval;
	}

	private ArrayList<Integer> mapGenerator(ArrayList<Integer> mapCoordinates) {



		ArrayList<Integer> col_data = new ArrayList<Integer>();
		ArrayList<Integer> mapPoints = new ArrayList<Integer>();
		int[] a = new int[] { 1, 10, 20, 30, 40, 50, 60, 70, 80 };
		int[] b = new int[] { 9, 19, 29, 39, 49, 59, 69, 79, 90 };
		int randomer;
		for (Integer d : mapCoordinates.subList(27, mapCoordinates.size())) {
			mapPoints.add(d);
		}
		try {
			for (int j = 0, k = 0; j <= mapPoints.get(k); j++) {
				if (j == mapPoints.get(k)) {
					// System.out.println("J = " + j + " is equaling D = " + mapPoints.get(k) + " so
					// negating");
					j = -1;
					if (k != mapPoints.size())
						k++;
					continue;
				} else {
					randomer = getRandomNumberInRange(a[k], b[k]);
					// System.out.println("Array so far is " + col_data);
					// System.out.println("Adding random number " + randomer + " that's between " +
					// a[k] + " & " + b[k]
					// + " when D = " + mapPoints.get(k) + ", K = " + k + " & J = " + j);
					if (!col_data.contains(randomer))
						col_data.add(randomer);
					else {
						randomer = getRandomNumberInRange(a[k], b[k]);
						if (!col_data.contains(randomer))
							col_data.add(randomer);
						else {
							randomer = getRandomNumberInRange(a[k], b[k]);
							if (!col_data.contains(randomer))
								col_data.add(randomer);
							else {
								randomer = getRandomNumberInRange(a[k], b[k]);
								if (!col_data.contains(randomer))
									col_data.add(randomer);
								else {
									randomer = getRandomNumberInRange(a[k], b[k]);
									if (!col_data.contains(randomer))
										col_data.add(randomer);
									else {
										randomer = getRandomNumberInRange(a[k], b[k]);
										if (!col_data.contains(randomer))
											col_data.add(randomer);
										else {
											randomer = getRandomNumberInRange(a[k], b[k]);
											if (!col_data.contains(randomer))
												col_data.add(randomer);
											else {
												randomer = getRandomNumberInRange(a[k], b[k]);
												if (!col_data.contains(randomer))
													col_data.add(randomer);
												else {
													randomer = getRandomNumberInRange(a[k], b[k]);
													if (!col_data.contains(randomer))
														col_data.add(randomer);

												}
											}
										}
									}
								}
							}

						}
					}
				}
			}
		} catch (Exception e) {
		}
		Collections.sort(col_data);
		// System.out.println(col_data.toString() + " has " + col_data.size());
		// System.out.println(col_data.toString());
		return col_data;
	}

	private static int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("Max is lesser than Min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	private void mapFiller(ArrayList<Integer> mapCoordinates, ArrayList<Integer> mapValues) {


		ArrayList<Integer> mapPoints = new ArrayList<Integer>();
		ArrayList<String> ticketNumbers = new ArrayList<String>();
		// System.out.println("Map co-ordinates are :\n\t\t\t" +
		// mapCoordinates.subList(0, 9) + "\n\t\t\t"f
		// + mapCoordinates.subList(9, 18) + "\n\t\t\t" + mapCoordinates.subList(18, 27)
		// + "\nAnd the sum array is:\n\t\t\t" + mapCoordinates.subList(27,
		// mapCoordinates.size()));
		// System.out.println("Ticket Numbers are " + mapValues.toString() + "\nCount =
		// " + mapValues.size());
		for (Integer d : mapCoordinates.subList(27, mapCoordinates.size())) {
			mapPoints.add(d);
		}
		try {
			for (int i = 0, j = 9, k = 18, a = 0; k < 27; i++, j++, k++) {
				if (mapCoordinates.get(i) == 1) {
					ticketNumbers.add(mapValues.get(a).toString());
					a++;
				} else
					ticketNumbers.add(" ");
				if (mapCoordinates.get(j) == 1) {
					ticketNumbers.add(mapValues.get(a).toString());
					a++;
				} else
					ticketNumbers.add(" ");
				if (mapCoordinates.get(k) == 1) {
					ticketNumbers.add(mapValues.get(a).toString());
					a++;
				} else
					ticketNumbers.add(" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("Ticket is :\n" + ticketNumbers + "\nTicket Size = " +
		// ticketNumbers.size());
//		 System.out.println(ticketNumbers);///// to check duplicates
		// for (int i = 1, j = 2, k = 3; k < ticketNumbers.size();) {
		// System.out.println(ticketNumbers.get(i) + "\t" + ticketNumbers.get(j) + "\t"
		// + ticketNumbers.get(k));
		// i = i + 3;
		// j = j + 3;
		// k = k + 3;
		// }

		for (int i = 0; i < ticketNumbers.size();) {
			System.out.print(ticketNumbers.get(i) + "\t");//// to present
			i = i + 3;
		}
		System.out.println("");//// to present
		for (int i = 1; i < ticketNumbers.size();) {
			System.out.print(ticketNumbers.get(i) + "\t");//// to present
			i = i + 3;
		}
		System.out.println("");//// to present
		for (int i = 2; i < ticketNumbers.size();) {
			System.out.print(ticketNumbers.get(i) + "\t");//// to present
			i = i + 3;
		}

	}

}
