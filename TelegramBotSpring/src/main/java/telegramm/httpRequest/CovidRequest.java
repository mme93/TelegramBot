package telegramm.httpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CovidRequest {

	public String getRWerthTotalGermany() {
		String messageReceived = "";
		try {
			URL url = new URL("https://covidwebservice.herokuapp.com/rValueTotalGermany");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoInput(true);
			int status = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuffer responseMsg = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				responseMsg.append(line);
				responseMsg.append(System.lineSeparator());

			}
			messageReceived = responseMsg.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageReceived;
	}

	public String getTotalTargetInfection(int rValue) {
		String messageReceived = "";
		try {
			URL url = new URL("https://covidwebservice.herokuapp.com/totalTargetInfection/" + rValue);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoInput(true);
			int status = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuffer responseMsg = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				responseMsg.append(line);
				responseMsg.append(System.lineSeparator());

			}
			messageReceived = responseMsg.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageReceived;
	}

	public String getTargetIncidenceForRWert(int rValue, int day) {
		String messageReceived = "";
		try {
			URL url = new URL("https://covidwebservice.herokuapp.com/targetIncidenceForRValue/" + rValue + "/" + day);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoInput(true);
			int status = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuffer responseMsg = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				responseMsg.append(line);
				responseMsg.append(System.lineSeparator());

			}
			messageReceived = responseMsg.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageReceived;
	}

	public String getAverageIncreaseDay(int day) {
		String messageReceived = "";
		try {
			URL url = new URL("https://covidwebservice.herokuapp.com/averageIncrease/" + day);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoInput(true);
			int status = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuffer responseMsg = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				responseMsg.append(line);
				responseMsg.append(System.lineSeparator());

			}
			messageReceived = responseMsg.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageReceived;
	}

	public String getPercenteInfection() {
		String messageReceived = "";
		try {
			URL url = new URL("https://covidwebservice.herokuapp.com/percentInfection");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoInput(true);
			int status = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuffer responseMsg = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				responseMsg.append(line);
				responseMsg.append(System.lineSeparator());

			}
			messageReceived = responseMsg.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageReceived;
	}

	public String getTotalInfection() {
		String messageReceived = "";
		try {
			URL url = new URL("https://covidwebservice.herokuapp.com/totalInfection");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoInput(true);
			int status = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuffer responseMsg = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				responseMsg.append(line);
				responseMsg.append(System.lineSeparator());

			}
			messageReceived = responseMsg.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageReceived;
	}

	public String getNewInfection() {
		String messageReceived = "";
		try {
			URL url = new URL("https://covidwebservice.herokuapp.com/newInfectionLastDay");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoInput(true);
			int status = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuffer responseMsg = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				responseMsg.append(line);
				responseMsg.append(System.lineSeparator());

			}
			messageReceived = responseMsg.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageReceived;
	}
	public String getDate() {
		String messageReceived = "";
		try {
			URL url = new URL("https://mycovidservice.herokuapp.com/date");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoInput(true);
			int status = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuffer responseMsg = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				responseMsg.append(line);
				responseMsg.append(System.lineSeparator());

			}
			messageReceived = responseMsg.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageReceived;
	}

	public String getAllInfos(String rWerth,String day) {
		String messageReceived = "";
		try {
			URL url = new URL("https://mycovidservice.herokuapp.com/allInfos/"+rWerth+"/"+day+")");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoInput(true);
			int status = con.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			StringBuffer responseMsg = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				responseMsg.append(line);
				responseMsg.append(System.lineSeparator());

			}
			messageReceived = responseMsg.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return messageReceived;
	}
}
