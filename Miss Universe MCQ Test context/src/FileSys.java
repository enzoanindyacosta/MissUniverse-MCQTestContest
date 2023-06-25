import java.io.*;
import java.util.*;

public class FileSys {

	// private static final String CONTESTANT_FILE = "./data/contestant.dat";
	// private static final String ANSWERS_FILE = "./data/answers.txt";
	// private static final String INPUT_FILE = "./data/inputdata.txt";
	// private static final int NAME_SIZE = 10;
	// private static final int COUNTRY_SIZE = 20;
	// private static final int PASSWORD_SIZE = 10;
	// private static final int REC_SIZE = NAME_SIZE + COUNTRY_SIZE + PASSWORD_SIZE;
	private static final File CONTF = new File("./data", "contestant.txt");
	private static final File QUESF = new File("./data", "inputdata.txt");
	private static final File ANSWF = new File("./data", "answers.txt");
	private static int totCont;
	private static int totQues;

	// get total number of contestant
	public static int getTotCont() {
		Scanner sfile;
		try {
			sfile = new Scanner(CONTF);
			String aLine = sfile.nextLine();
			Scanner sline = new Scanner(aLine);
			totCont = Integer.parseInt(sline.next());
			sline.close();
			sfile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File to read " + CONTF + " not found!");
		}
		return totCont;
	}

	// get total number of questions
	public static int getTotQues() {
		Scanner sfile;
		try {
			sfile = new Scanner(QUESF);
			String aLine = sfile.nextLine();
			Scanner sline = new Scanner(aLine);
			totQues = Integer.parseInt(sline.next());
			sline.close();
			sfile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File to read " + QUESF + " not found!");
		}
		return totQues;
	}

	// load contestant list with contestant data from text file
	public static void readContestantFile(LinkedList<Contestant> cL) {
		Scanner sfile;
		String contName, contCountry, contPassword;
		Contestant contestant;
		try {
			sfile = new Scanner(CONTF);
			String aLine = sfile.nextLine();
			Scanner sline = new Scanner(aLine);
			totCont = Integer.parseInt(sline.next());
			for (int k = 1; k <= totCont; k++) {
				aLine = sfile.nextLine();
				sline = new Scanner(aLine);
				sline.useDelimiter(":");
				contName = sline.next();
				contCountry = sline.next();
				contPassword = sline.next();
				sline.close();
				contestant = new Contestant(contName, contCountry, contPassword);
				cL.add(contestant);
			}
			sfile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File to read " + CONTF + " not found!");
		}
	}

	// load question list with question data from text file
	public static void readQuestionFile(LinkedList<Question> qL) {
		Scanner sfile;
		int type;
		char answer;
		String theQues;
		String choices[] = new String[4];
		String quesPic;
		Question ques;
		try {
			sfile = new Scanner(QUESF);
			String aLine = sfile.nextLine();
			Scanner sline = new Scanner(aLine);
			totQues = Integer.parseInt(sline.next());
			for (int k = 1; k <= totQues; k++) {
				aLine = sfile.nextLine();
				sline = new Scanner(aLine);
				sline.useDelimiter(":");
				type = Integer.parseInt(sline.next());
				answer = sline.next().charAt(0);
				theQues = sline.next();
				quesPic = "";
				if (type == 2)
					quesPic = sline.next();
				choices[0] = sline.next();
				choices[1] = sline.next();
				choices[2] = sline.next();
				choices[3] = sline.next();
				sline.close();
				ques = new Question(type, answer, theQues, choices, quesPic);
				// System.out.println(ques.getAnswer());
				qL.add(ques);
			}
			sfile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File to read " + QUESF + " not found!");
		}
	}

	// read contestant answers from text file according to the contestant index
	public static void readAnswerFile(String answer[], int cIndex) {
		Scanner sfile;
		try {
			sfile = new Scanner(ANSWF);
			sfile.useDelimiter(",");

			while (sfile.nextInt() != cIndex) {
				sfile.nextLine();
			}
			for (int i = 0; i < getTotQues(); ++i) {
				answer[i] = sfile.next();
			}
			sfile.close();
		} catch (IOException e) {
			System.out.println("File to read " + ANSWF + " not found!");
		}
	}

	public static void writeAnswerFile(String answer[], int cIndex) { // pass in array of contestant answer and contestant
																																		// index for update
		String writeContent = Integer.toString(cIndex);
		writeContent += ",";
		for (int i = 0; i < answer.length; ++i) {
			writeContent += answer[i];
			writeContent += ",";
		}
		try {
			// input the (modified) file content to the StringBuffer "input"
			BufferedReader file = new BufferedReader(new FileReader(ANSWF));
			StringBuffer inputBuffer = new StringBuffer();
			String line;
			int cur = 0;
			while ((line = file.readLine()) != null) {
				if (cur == cIndex)
					line = writeContent;
				inputBuffer.append(line);
				inputBuffer.append('\n');
				cur++;
			}
			file.close();

			// write the new string with the replaced line OVER the same file
			FileOutputStream fileOut = new FileOutputStream(ANSWF);
			fileOut.write(inputBuffer.toString().getBytes());
			fileOut.close();
		} catch (IOException e) {
			System.out.println("File to read " + ANSWF + " not found!");
		}
	}

	// get contestant image from another directory
	public static String[] fetchContestantImage(int contIndex) {
		String images[] = new String[3];
		String fn = "file:data/images/contestant/";
		switch (contIndex) {
		case 0:
			images[0] = fn + "victor1.jpg";
			images[1] = fn + "victor2.jfif";
			images[2] = fn + "victor3.jpg";
			break;
		case 1:
			images[0] = fn + "yaseen1.jpeg";
			images[1] = fn + "yaseen2.jpeg";
			images[2] = fn + "yaseen3.jpeg";
			break;
		case 2:
			images[0] = fn + "ali1.jpeg";
			images[1] = fn + "ali2.jpeg";
			images[2] = fn + "ali3.jpeg";
			break;
		case 3:
			images[0] = fn + "katrina1.jpeg";
			images[1] = fn + "katrina2.jpeg";
			images[2] = fn + "katrina3.jpeg";
			break;
		case 4:
			images[0] = fn + "enzo1.jpg";
			images[1] = fn + "enzo2.jpg";
			images[2] = fn + "enzo3.jpg";
			break;
		default:
			break;
		}
		return images;
	}

	// get country images from another directory
	public static String fetchCountryImage(int contIndex) {
		String fn = "file:data/images/contestant/";
		switch (contIndex) {
		case 0:
			fn += "Timor-Leste.png";
			break;
		case 1:
			fn += "Togo.png";
			break;
		case 2:
			fn += "Thailand.png";
			break;
		case 3:
			fn += "Syria.png";
			break;
		case 4:
			fn += "Tajikistan.jpg";
			break;
		default:
			break;
		}
		return fn;
	}

}

/*
 * public static void initContestantRAF() throws IOException { RandomAccessFile
 * contestantRAF = new RandomAccessFile(CONTESTANT_FILE, "rw"); Scanner sfile;
 * try { sfile = new Scanner(contF); String aline = sfile.nextLine(); Scanner
 * sline = new Scanner(aline); totCont = Integer.parseInt(sline.next()); for
 * (int k = 1; k <= totCont; k++) { aline = sfile.nextLine(); sline = new
 * Scanner(aline); sline.useDelimiter(":"); contName = sline.next(); contCountry
 * = sline.next(); contPassword = sline.next(); System.out.println(contName +
 * " " + contCountry + " " + contPassword); contestant = new
 * Contestant(contName, contCountry, contPassword);
 * serializeContestantFile(contestantRAF, k, contestant); sline.close(); }
 * sfile.close(); } catch (FileNotFoundException e) {
 * System.out.println("File to read " + contF + " not found!"); }
 * contestantRAF.close(); }
 * 
 * public static void serializeContestantFile(RandomAccessFile file, int noCont,
 * Contestant contestant) throws IOException { int filePosition = (noCont - 1) *
 * REC_SIZE; file.seek(filePosition); System.out.println("File position : " +
 * filePosition); writeString(file, contestant.getName(), NAME_SIZE);
 * writeString(file, contestant.getCountry(), COUNTRY_SIZE); //
 * writeString(file, contestant.getPassword(), PASSWORD_SIZE); }
 * 
 * public static void displayRecords(RandomAccessFile file) throws IOException {
 * String contName, contCountry, contPassword; long numRecords = file.length() /
 * REC_SIZE; file.seek(0); for (int i = 0; i < numRecords; i++) { contName =
 * readString(file, NAME_SIZE); contCountry = readString(file, COUNTRY_SIZE);
 * contPassword = readString(file, PASSWORD_SIZE); System.out.println("Name : "
 * + contName + " COUNTRY : " + contCountry + " PASSWORD : " + contPassword); }
 * }
 * 
 * public static void writeString(RandomAccessFile file, String text, int
 * fixedSize) throws IOException { int size = text.length(); if (size <=
 * fixedSize) { file.writeChars(text); for (int i = size; i < fixedSize; i++)
 * file.writeChar(' '); } else { file.writeChars(text.substring(0, fixedSize));
 * } }
 * 
 * public static String readString(RandomAccessFile file, int fixedSize) throws
 * IOException { String value = ""; for (int i = 0; i < fixedSize; i++) value +=
 * file.readChar(); return value; }
 */