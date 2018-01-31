package edison;
	/*
	 * @Author: Edison Zhong
	 * 
	 * 
	 * 
	 */
public class Output2 {
	// greenSN: if traffic light on Snell road is green
	 boolean greenSN;
	 
		// greenWE: if traffic light on Weaver road is green
	 boolean greenWE;
	 
	 //time: how many time left in current light turning action
	 int time;
	 //number of cars that are waiting on the LEFT lane of east, west, north and south
	 int El,Wl,Nl,Sl;
	 
	 //number of cars that are waiting on the RIght lane of east, west, north and south
	 int Er,Wr,Nr,Sr;
	 
	 //if the first car on Snell road just start moving 
	 boolean startSN;
	 
	 //if the first car on Weaver road just start moving 
	 boolean startWE;
	 int totalTime;
	 
	//constructor
	public Output2() {
		this.greenSN = false;
		this.greenWE = false;
		this.time = 0;
		this.El = 0;
		this.Wl = 0;
		this.Sl = 0;
		this.Nl = 0;

		this.Er = 0;
		this.Wr = 0;
		this.Sr = 0;
		this.Nr = 0;
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
		System.out.println(totalTime + ": N = " + (Nr + Nl) + "; "
				 + ": S = " + (Sr + Sl) + "; "
				 + ": E = " + (Er + El) + "; "	
				 + ": W = " + (Wr + Wl));
	}
	
	void printResults() {
		while(time >=1) {

			System.out.println(totalTime + ": N = " + (Nr + Nl) + "; "
										 + ": S = " + (Sr + Sl) + "; "
										 + ": E = " + (Er + El) + "; "	
										 + ": W = " + (Wr + Wl));
		
			//if it is not the third car that will turn left
			if(((totalTime + 1) % 3 != 0) || (totalTime == 0)) {
			if(greenSN) {
				
				if(startSN) {
					if(Sl > 0)Sl++;
					if(Nl > 0)Nl++;
					startSN = false;
					// if no traffic from opposite direction
					if(Sr > 0)Sr--;
					if(Nr > 0)Nr--;
				}
				Wl++;
				El++;
			}else if(greenWE) {
				if(startWE) {
					if(Wl > 0)Wl++;
					if(El > 0)El++;
					startWE = false;
					// if no traffic from opposite direction

					if(Wr > 0)Wr--;
					if(Er > 0)Er--;
				}
				Sl++;
				Nl++;
				
			}
			else if(!greenSN && !greenWE) {//if all traffic lights are red
				Wl++;
				El++;
				Sl++;
				Nl++;
				if(Sr > 0)Sr--;
				if(Nr > 0)Nr--;
				if(Wr > 0)Wr--;
				if(Er > 0)Er--;
			}
			
		}else{// if it is the third car that will turn left
			if(greenSN) {
				Wr++;
				Er++;
				if(startSN) {// if no traffic from opposite direction
					startSN = false;
				}else {
					Sr++;
					Nr++;
					if(Sl > 0)Sl--;
					if(Nl > 0)Nl--;
				}
			}else if(greenWE) {
				Sr++;
				Nr++;
				
				if(startWE) {// if no traffic from opposite direction
					startWE = false;
				}else {
					Wr++;
					Er++;

					if(Wl > 0)Wl--;
					if(El > 0)El--;
				}
				
			}else if(!greenSN && !greenWE) {//if all traffic lights are red
				Wl++;
				El++;
				Sl++;
				Nl++;
				if(Sr > 0)Sr--;
				if(Nr > 0)Nr--;
				if(Wr > 0)Wr--;
				if(Er > 0)Er--;
			}
			
		}
			time--;
			totalTime++;
		
		}
	}
	
	public static void main(String[] args) {
		Output2 o2 = new Output2();
		
		//input the command about traffic light
		//s: Snell road w: Weaver road g:green light r:red light 
		//number: how long will this action be
		String s = "sg3 sr1 wg3 wr1";
		
		o2.runInput(s.split(" "));
		
	}
}
