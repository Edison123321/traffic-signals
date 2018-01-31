package edison;

public class Output1 {
	 boolean greenSN;
	 boolean greenWE;
	 int time;
	 int E,W,N,S;
	 boolean startSN;
	 boolean startWE;
	 int totalTime = 0;
	
	public Output1() {
		this.greenSN = false;
		this.greenWE = false;
		this.time = 0;
		this.E = 0;
		this.W = 0;
		this.S = 0;
		this.N = 0;
		this.startSN = false;
		this.startWE = false;
		this.totalTime = 0;
	}
	
	void runInput(String[] str){

		
		for(int i = 0; i< str.length; i++) {
			if(str[i].charAt(0) == 's') {
				if (str[i].charAt(1) == 'g') {
					greenSN = true;
					startSN = true;
				}else if(str[i].charAt(1) == 'r'){
					greenSN = false;
				}
			}else if (str[i].charAt(0) == 'w') {
				if (str[i].charAt(1) == 'g') {
					greenWE = true;
					startWE = true;
				}else if(str[i].charAt(1) == 'r'){
					greenWE = false;
				}
			}
			time = Integer.parseInt(str[i].substring(2, str[i].length()));
			//System.out.println(str[i].substring(2, str[i].length()));
			printResults();
		}

		System.out.println(totalTime + ": N = " + N + "; "
									 + ": S = " + S + "; "
									 + ": E = " + E + "; "	
									 + ": W = " + W);
	}
	
	void printResults() {
		while(time >=1) {

			System.out.println(totalTime + ": N = " + N + "; "
										 + ": S = " + S + "; "
										 + ": E = " + E + "; "	
										 + ": W = " + W);
			
			if(greenSN) {
				if(startSN) {
					if(S > 0)S++;
					if(N > 0)N++;
					startSN = false;
				}
				W++;
				E++;
			}else if(greenWE) {
				if(startWE) {
					if(W > 0)W++;
					if(E > 0)E++;
					startWE = false;
				}
				S++;
				N++;
			}else if(!greenSN && !greenWE) {
					S++;
					N++;
					W++;
					E++;
			}
			
			time--;
			totalTime++;
		}
	}
	
	public static void main(String[] args) {
		Output1 o1 = new Output1();
		
		String s = "sg3 sr1 wg3 wr1";
		
		o1.runInput(s.split(" "));
		
	}
}
