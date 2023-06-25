public class Contestant {

	private String name;
	private String country;
	private String password;
	private String[] answer = new String[25];
	private double score;
	
	public Contestant(String n, String c, String p) {
		name = n;
		country = c;
		password = p;
	}

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	public String[] getAnswer() {
		return answer;
	}

	public double getScore() {
		return score;
	}

	public String getPassword() {
		return password;
	}

	public void setAnswer(int no, String ans) {
		answer[no] = ans;
	}

	public void setScore(int n) {
		double percentage = (n/25.00) * 100;
		String format = String.format("%.2f", percentage);
		score = Double.parseDouble(format);
	}

	// check if contestant has attempted at all
	public boolean checkAttempt(Contestant c) {
		int tmp = 0;
		for (int i = 0; i < 25; ++i) {
			if (answer[i] == "X") tmp++;
		}
		System.out.println("attempts : " + tmp);
		return (tmp == 25 ? false : true);
	}

	// count how many questions attempted by contestant
	public int checkAttempted() {
		int tmp = 0;
		for (int i = 0; i < 25; ++i) {
			if (answer[i] != "X") tmp++;
		}
		System.out.println("attempted : " + tmp);
		return tmp;
	}

}
