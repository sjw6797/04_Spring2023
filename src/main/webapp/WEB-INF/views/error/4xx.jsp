<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#content {
		margin-left: auto;
	    margin-right: auto;
	    margin-top: 100px;
	    display: -ms-flexbox;
	    display: flex;
	    flex-direction: row;
	    justify-content: center;
	}
	
	
	#wrap {
		display: flex;
	}
	
	.previous-page-btn{
		display: inline-block;
	    width: 100px;
	    height: 30px;
	    font-size: 16px;
	    text-align: center;
	    vertical-align: middle;
	    user-select: none;
	    position: relative;
	    
	    color: rgb(0, 164, 155);
  		background: white;
  		border: 3px solid rgb(0, 164, 155);
  		box-sizing: border-box;
	}
	
	.previous-page-btn, .previous-page-btn:hover {
	    color: rgb(0, 164, 155);
	}
	a {
		text-decoration: none;;
	}
</style>
</head>
<body>
<div id="content">
	<div id="wrap">
		<div style="flex: 0 1 auto; margin-right: 30px;">
			<img style="width: 160px; height: 120px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKAAAAB4CAYAAAB1ovlvAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA9lJREFUeNrs3TFy20YUgGHQTuHOqlXRJzA1PIChE8QpU5k6gc0T2DqB7NKVlC5dfAPTB+BYOUGUSi1bV85uuJ7ZIBIpj6EFCH7/DIYArBkZb3+9t7sEFlUFAAAAAAAAAAAwVEZd/wem0+kkfMRtrDmKchW2y+VyebmXAgbxnoePM+L1QsR5EPHD3ggY5DsPHzNt3ysugoQngxdwg3wLDhSl7oOEo8LyxbL7R3ZqldL/BR86qUSz1A06yE7/UrIcPyh8zWcN+Y7J1x0p9sepLW5qo+EImEa7+YBj3vUIDP9KGNtgnp0ap7YaXAbM+xwrma93mXC1pX+48wLm/QyZr39c3tJWg+oDAgQEAQECgoAgIEBAEBAgIAgIEBAEBAgIAgIEBAGxjfjAVdj+Ss9agIBF5aur9dN+47C9FhECliaX7lQ4CFgy+8WsV6dDz7sQsDgvs33yEbA4z7P934Tj7vwkBHcqsd8GF4tQXhc3lN9xVn498ScDtipfXCngPA0yPqblRXLqbJ98BGydWeP4ReP4abb/SbgI2DarLceTDf8GAv4wpw3B3m34WSWYgO2S5vSeVOtVpJ4YZBgFdyHhVbVeyhYyIAgIKMHl2DQRXf1/Xb2FiMmAbcq3bSL6z2z/sYgRsG1mjePmRHQ+Kp4IFwHbZttEdF5y65AhD4SMgG2ycSI69AlXjSxYCxkBW6MxEX10y0R0fgvWz6JGwLYlvIqj3zQhfRMXWWmepVu0QMBigjZL80xUCFiat1kWfCkcBOwiC54mEY9E5G74JqRdCd+KggwIAgIEBAEBAoKAAAFBQICAICBAQBAQBAQICAICHdDV/YCT6XT6Ufh7xWSfBIzPztbaHEow9rIEX1VeZ9A34pIj470RcLlcvtHm/SH0yZ91IaASDH1AEBDYKwEtYdY/DoYu4H8WcrSOXq8GILEtJre01SAFjLzS9L3h1Za22n0B09Jmi+zU67T4N7rNfrEN8re9b1qGrnVGhS82pvnPjdMfqvWk9CIt8IMyJbeu1pPPzUXXj0q+DWrU0V/cOQ16yUlaEbYYD0tf4fX19eXh4eHf6S/wkTbvBbHy/Brk+730L37YxdUmCd+H3S/V+usfI+JuiH29d0m+Tl7COOpLJEJprnes8Zr3M86rHXpd6w1vfKr2WsAd7Mh/bZw67kuj7hK+igMBQUCAgCAgQEAQECAgCAgQEAQECIjhMdibEdLd12f3+CvqxnG8E+a+7uied3W71H0z5Ne1nlVlV+Ca3PO1HCvBu8XKtciAXXIStk/V7t9tHeW70FsEAAAAAAAAAAAA8B38I8AA+oL+8NsgB2IAAAAASUVORK5CYII=">
		</div>
		<div style="flex: 0 1 auto; display: flex; flex-direction: column;">
			<div>
				<img style="vertical-align: top; width: 120px; height: 72px;" src="data:image/png;base64,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">
				<img style="vertical-align: top; width: 120px; height: 72px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAABICAYAAAA9HjF/AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAABIFJREFUeNrsmv9R4lAQx+ON/x92ECuQGws4qECsQKgAUgFSAVABsQKxAmMBjrEC6eBiBXfvOYuTi9l9v/I4bub7mXmjQLIsb/ftL0gSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABHwYn04uXlZV/92ajVd5RbqpU9Pz8XLTK1vJFaPeH+/X2VWk9qbZWsnUHXHuk6Ei6rSDeNlvdqI9uwP3qltae1rFLJLB1kLNUaOOi935Mq1MCPhjeWKJQCw4a8MRnBSx45Tcnoeqv+zD1l5yS7sjCIdqSZWjcNwzbRhrhTayXJVfLuDU4psVJrIcn/duCIkQbcqx3tkTy+a8Yku2cwrtbhhRwptfis+roXg869AL1nJr0PbeBQejaG8EQb4V4w7ocTeDhpGtEx93pv/oWBHyIaeRZJ9kAZYiTkyVDHTCPpPeIcyNfAOg8OhXWu8sLKQZ7OV2fqnpP9Uo+va4VFkyvH3H1Wk3um1oQKF1vZS4uicFErDjkju9Qf28Z+aL0zQe/WPH7qaeDWCjmALxWherxVXqkN/MaEJVue6rLp/1zJTpgNT1vy7kBw9Ot6FU6n9J7RUUeIvmWFvW7sh9Z7RemprZi8OOYc/N72JG3cLtJ7bi2vuxGizrDZYtHjoXDSpoF6Fy7Fmq+BBwc0fqy8ZVuocS0M257Q89kR7J13iJ6rUPFTCIm3jvK+C20J1/h30RrZDCF6nhFgy6UAHWZtem6PiNKZgSVP1HmmcMzRugr8PBGUZ0ZC5eoi+4IZVMwtqn/OuKXJQPp1qiH6TA1h+gzT+jWU26eCYz51beCuw/AvKny6bsG08/z2zM1pYAQJiTQueldcRPnfBh0fp1edjjyS7FWjaEqF6vmYYOuBWAauIsktqT+OQa42KXOYHh0LuTRzOA0w4Fo4YTE8fJEYBvcBn2XBbFKR+H+BERut90TPC2JU0VnEMMmxi2DcfSHl6pD9wOuqQ+ntG6K7Hj5oeXoUdy4ovfT8kmFLo74fguw5UwmzQwXTlwfU4vUYuaWD3tLQZBnLwF3zMao0TIF8v2R4rW1qJrR2I8eCyhS6p5798551zckyodIexDBw19OY98YUaC0MWFLfN6HNKhxPw52wuWPm9I4TfgL24KF37utoMSZZnxsTkKdXdAJ6jCFCKukF46B6wnTbMoXLaRPbdNmQw+lKdlcbRnCRpgrYE32KH5noM+bkhoTogWFtAk6adIqNYcniFHObPG3medJlITm7Wm80lHgzpJEsUvSZc/XJMQ86VkIxF9q6LITKdN6yuSuH3Cn1q3kkvVPOsY7WwIaTM+Dyn6XsnXCKZ0yenyRuM/CmcScd7Il0iqdtp9hk4JCBRcUMDqz7QkNxcWPZWxbCaeAixLjN4ehXoovEbRadGYxbOraimdBlfCnsTkwaUvvQ9zBu6++NqX+sK1KapjEtv6pold+i61bqOcnjB417donhN8d0n3aCK6ZgK6hazi1/ilvXW19fxNAbAAAAAAAAAAAAAAAAAAAAgL/5I8AAir4IeAj2xkcAAAAASUVORK5CYII=">
			</div>	
			<div style="font-size: 24px; margin-bottom: 32px;">페이지를 찾을 수 없습니다</div>
			<div style="font-size: 14px; margin-bottom: 32px;">페이지가 존재하지 않거나, 사용할 수 없는 페이지입니다.<br>입력하신 주소가 정확한지 다시 한 번 확인해주세요.</div>
			<div>
				<a class="previous-page-btn" href="javascript:history.back();" style="width: 100px; height: 30px;">이전페이지</a>
				<a class="previous-page-btn" href="/" style="background: rgb(0, 164, 155); color: white;">홈으로 가기</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>