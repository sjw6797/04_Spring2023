<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta charset="UTF-8">


<article>
	<h1>예약변경 및 취소 규정</h1>
	<!-- <form> -->
	<form method="post" action="" >		

		<h1>변경</h1>
		<fieldset>
			<h4>
				- 예약 후 탑승자 정보와 항공 일정은 변경이 불가하며, 취소 후 재예약하셔야 합니다. <br> - 변경에 따른 취소는 항공사 취소수수료가 발생하며, 재예약 시 요금이 변경되거나 좌석이 마감될 수 있습니다. <br> - 또한, 항공권 취소에 대한 책임은 탑승자 본인에게 있습니다.
			</h4>

		</fieldset>

		<h1>예약자 개인정보 취소 방법</h1>
		<fieldset>
			1. 출발 전 전체 취소 방법<br> <br> · 출발 전 전체취소는 [마이페이지 > 예약조회 > 상세보기 > 전체취소]에서 취소 가능합니다.<br> ※ 사전좌석배정 or 체크인된 상태에서는 취소가 불가하오니, 항공사 홈페이지에서 체크인 해제 후 마이페이지에서 취소하시기 바랍니다.<br> <br> 2. 출발 후(미탑승 항공권) 전체취소 방법<br> <br> · 출발 후 항공권(미탑승 항공권) 취소는 EZEN AirLine [마이페이지 > 온라인 문의] 를 통해 접수 신청하시기 바랍니다.<br> ※ 미탑승 항공권은 각 항공사 취소수수료가 부과됩니다. <br>

		</fieldset>

        
		<input type="buttons" value="예약취소하기" id="delete" class="submit" />
		
		
		<input type="buttons" value="예약변경하기" id="update" class="submit" />
		
		
		
		<br> <br>

	</form>
	
	
	    <form method="post" action="updateReservForm" >		
                <input type="submit" value="예약변경하기"  class="submit" />
        </form>
        
        
        
</article>