package com.ezen.flight_info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class FlightApp {

	// 주어진 출발지, 도착지, 출발일자 조건에 대한 검색결과 건수 값을 읽어옴
	// 내부적으로는 출발지, 도착지, 출발일자 외에 페이지 1로 지정하여 FlightInfo 객체를 가져와 거기에서 totalCount 값을 가져오는
	// 방식을 취한다. 주어진 검색조건에 대하여 검색결과가 0건이든 10건이든 또는 100건이든 기본적으로 1페이지의 결과는 반환하기 때문이다.
	// 다만, 서버에서 반환하는 Json 형식에 문제가 있어 0건일 때는 JsonSyntaxException이 발생하여 getFlightInfo()가
	// null을 반환받는 결과가 되므로 이에 주의해야 한다.
	public static int getTotalCount(String depAirportId, String arrAirportId, long depPlandTime) {

		int totalCount = 0;

		FlightInfo flightInfo = getFlightInfo(depAirportId, arrAirportId, depPlandTime, 1);

		if (flightInfo == null) // 조회결과 건수가 0일때 JsonSyntaxException으로 인해 null 반환하므로
			totalCount = 0;
		else
			totalCount = flightInfo.getResponse().getBody().getTotalCount();

		return totalCount;
	}

	public static FlightInfo getFlightInfo(String depAirportId, String arrAirportId, long depPlandTime, int page) {
		// (1) URL 주소로부터 스트림을 연결하고    (2) 스트림으로부터 읽어온 JSON 데이터를 Gson을 이용하여 Java 클래스 객체들로 변환시켜 출력해 본다.
		FlightInfo flightInfo = null;
		StringBuilder sb = null;

		try {
			URL url = new URL(
					"https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=83umnw1r6dNQ5bxKMvcWYE54g9c%2FnRV81xsRdpYJ3uf2c5VCaBHx1%2BJ%2FS0bkx62BxgaOuuSdnBsIz6%2BpPeUuEw%3D%3D&numOfRows=50&pageNo="
							+ page + "&depAirportId=" + FlightInfoService.airPortId.get(depAirportId) + "&arrAirportId="
							+ FlightInfoService.airPortId.get(arrAirportId) + "&depPlandTime=" + depPlandTime
							+ "&_type=json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			sb = new StringBuilder();

			String input = "";
			
			// 연산순서
			// 1) input = br.readLine() : input에 값 대입
			// 2) input != null : input이 null 과 다른지 비교하여 다르면 while문 본체 실행. null인 경우 while문 벗어남
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			br.close();
			conn.disconnect();

			Gson gson = new Gson();
			flightInfo = gson.fromJson(sb.toString(), FlightInfo.class);

		} catch (JsonSyntaxException e) {
			/*
			 * Gson은 items의 value값으로 JSON 객체가 오기를 기대하고 오브젝트의 시작을 표시하는 '{'가 올거로 예상하고 있는데 특이하게
			 * 조회결과 건수가 0일 때는 공공데이터포털 서버는 items의 value값으로 ""라는 빈 문자열을 반환한다. 즉, JSON 오브젝트가
			 * 와야할 자리에 문자열이 오고 있기 때문에 JsonSyntaxException 에러를 발생시키게 되는 것이다. 이 예외 발생시
			 * gson.fromJson()에서 예외 발생하고 이때는 flightInfo는 채 값이 세팅되지 못해 null을 반환하게 된다.
			 */
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("flightInfo값 : " + flightInfo);
		return flightInfo;
		
	}

	public static void main(String[] args) {

		// 조회가능한 출발지, 도착지 공항이 어디어디인지 일단 리스트로 보여줌
		for (String key : FlightInfoService.airPortId.keySet()) {
			System.out.print(key + " ");
		}

		System.out.println();

		System.out.println("출발지를 입력하세요");
		Scanner sc = new Scanner(System.in);
		String depAirportId = sc.nextLine(); // 김포

		System.out.println("도착지를 입력하세요");
		String arrAirportId = sc.nextLine();

		System.out.println("출발일자를 입력하세요"); // 입력형식 : 20200415
		long depPlandTime = sc.nextLong();

		int totalCount = getTotalCount(depAirportId, arrAirportId, depPlandTime);

		// 바로 위의 getTotalCount() 호출에서 결과가 0건인 경우에는 아래 if문만 실행하고 종료함
		if (totalCount == 0) {
			System.out.println("출발지:" + depAirportId + " / 도착지:" + arrAirportId + " / 출발일자:" + depPlandTime);
			System.out.println("지정한 조건에 대한 비행편이 존재하지 않습니다.");
			return;
		}

		// totalCount 0건이 아닌 경우는 코드는 아래로 진행함

		// 항공기운항정보 페이지에 페이지당 50건씩 출력하도록 설정되어 있으므로
		// totalCount로부터 몇페이지까지 읽어와야 하는지를 알아낼 수 있다.
		// 50으로 나누어 떨어지는 경우는(나머지가 0) 50으로 나눈 몫을 pageCount에 넣고
		// 나머지가 있는 경우는 50으로 나눈 후 1을 더한 값을 pageCount에 넣도록 함
		// 50건 100건인 경우는 각각 1페이지 2페이지로 충분하지만
		// 51건, 101건인 경우는 각각 2페이지 3페이지가 되어야 하므로
		int pageCount = (totalCount % 50 == 0) ? (totalCount / 50) : (totalCount / 50 + 1); // 삼항연산자

		// 아래 for문이 돌면서 동일한 출발지, 도착지, 출발일자에 대해 검색결과 건수에 따라 필요한 페이지수만큼 반복해서 FlightInfo 객체를 가져온다.
		for (int page = 1; page <= pageCount; page++) {
			FlightInfo flightInfo = getFlightInfo(depAirportId, arrAirportId, depPlandTime, page);
			List<Item> flightItems = flightInfo.getResponse().getBody().getItems().getItem();

			// 하나의 FlightInfo 객체 안에는 최대 50개까지의 검색결과 건수가 담길 수 있으므로 아래 for문으로 회전하면서 하나씩 출력함
			for (Item item : flightItems) { // forEach 문
				System.out.println("항공사 : " + item.getAirlineNm());
				System.out.println("출발지 : " + item.getDepAirportNm());
				System.out.println("도착지 : " + item.getArrAirportNm());
				System.out.println("출발시간 : " + item.getDepPlandTime());
				System.out.println("도착시간 : " + item.getArrPlandTime());
				System.out.println("일반석요금 : " + item.getEconomyCharge());
				System.out.println("비즈니스석요금 : " + item.getPrestigeCharge());
				System.out.println();
			}
		}

		System.out.println();
		System.out.println("총 건수: " + totalCount);

	}
}
