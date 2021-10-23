package model;

import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 * @author Blot Elliott
 *
 */


/*
* Represent an event
*
* @param  ligne  all the event
* @param  from  get from where the event came
* @param  to  get where the event was going to
* @param  session  get the parameters from the session
* @param  label  get the label from the event
* @param  separator  get the separator of the event
* @param  params  represent all the parameters
* @param  paramsSess  represent all the parameters useful in order to use the event
* @param  date  represent the timestamp of the event
*/


public class Event {
	public String ligne;
	public static String from = "Host";
	public static String to = "Dest";

	private String session;
	
	private String label;
	private ArrayList<String> params;
	private ArrayList<String> paramsSess;
	public String date;  //public for debug only

	public Event(String line){
		this.ligne = line;
		label= SeparationWhenNoRegex.nameOfAnEvent(line);
		params = SeparationWhenNoRegex.separationWithoutRegex(line);
		paramsSess = new ArrayList<String>();
	}
	

	public Event(String line, Matcher m) {
		ligne= line;
		date = m.group("date");
		label = m.group("label");
		params = new ArrayList<String>();
		paramsSess = new ArrayList<String>();
		int n = 1;
		try {
			while(m.group("param" + n) != null) {
				
				if (m.group("param" + n).contains("session=")) {
					//System.out.println(m.group("param" + n));
					session = m.group("param" + n);
				}
				else {
					params.add(m.group("param" + n));
					
				}
				if((!m.group("param" + n) .contains("Host=")) && (!m.group("param" + n) .contains("Dest="))){
					paramsSess.add(m.group("param" + n)); 
				}
				n++;
			
			}
		}catch(IllegalArgumentException e) {
			//end of while
			System.out.println(e);
		}
		
	}
	
	/**
	 * Return the label of the event.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Return the parameters of the event, without session identifier.
	 */
	public ArrayList<String> getParamsWithoutFromTo() {
		return paramsSess;
	}
	
	/**
	 * Return all the parameters.
	 */
	public ArrayList<String> getparamsWithFromTo() {
		return params;
	}
	
	/**
	 * Return the source of the event.
	 */
	public String getFrom() {
		String res = "";
		for (String param:params) {
			if (param.startsWith(from + "=")){
				return param.substring(from.length() + 1);
			}
		}
		System.err.println("no From in :" + this.toString());
		System.exit(3);
		return res;
	}

	/**
	 * Return the destination of the event.
	 */
	public String  getTo() {
		String res = "";
		for (String param:params) {
			if (param.startsWith(to + "=")){
				return param.substring(to.length() + 1);
			}
		}
		System.err.println("no From in :" + this.toString());
		System.exit(3);
		return res;
	}

	public String getligne() {
		return this.ligne;
	}

	
	public String toString() {
		String res = label + "(";
		//res = res + "date=" + date + ";";//for debug only
		String separator = ",";
		for (String param: params) {
			res = res + param + separator;
		}
		res = res.substring(0, res.length()- separator.length());
		res = res +")";
		return res;
	}

	/**
	 * Check if ai has common parameters with this.
	 */
	public boolean dataSimilarity(Event ai) {
		ArrayList<String> paramsi = ai.getparamsWithFromTo();
		ArrayList<String> paramsj = this.getparamsWithFromTo();
		for (String parami: paramsi) {
			if (!(parami.contains(from) || parami.contains(to))) {
				for (String paramj: paramsj) {
					if (paramj.equals(parami)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Check if the event is a request.
	 */
	public boolean isReq() {
		if (!this.toString().contains("esponse") & !this.toString().contains("Resp") & !this.toString().contains("resp") & !isInter()) {
			//System.out.println(this);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Check if the event is a response.
	 */
	public boolean isResp() {
		if (this.toString().contains("esponse") | this.toString().contains("Resp") | this.toString().contains("resp")) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Check if the event is a non-communicating action.
	 */
	public boolean isInter() {
		if (!this.toString().contains("Host=") | !this.toString().contains("Dest=")) {
			return true;
		}
		if (getFrom().equals(getTo())) {
			//System.out.println(this);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Return the session identifier.
	 */
	public String getSessionID() {
		return session;
	}

}

