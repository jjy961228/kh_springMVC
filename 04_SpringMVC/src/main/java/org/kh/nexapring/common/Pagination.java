package org.kh.nexapring.common;

import org.kh.nexapring.board.domain.PageInfo;

//Pagination -> Controller의 pageInfo pi 에 값을 셋팅해준다
//pi는 계산식이 필요한 부분이 많아서 코드가 지저분해지기 때문에 클래스를 하나 만들어서 해줐다
public class Pagination {
	public static PageInfo getPageInfo(int currentPage, int totalCount) {
		PageInfo pi = null;
		// pageNavi: navi의 갯수
		int boardLimit = 5; //한 페이지당 보여주는 게시물의 갯수
		int naviLimit = 5;	// 한 페이지당 보여줘야하는 pageNavi의 갯수
		int maxPage;		// 최대 페이지 갯수(pageNavi의 최댓값)
		int startNavi;		// 각 페이지의 시작 번호(Navi시작값)  1페이지일때, 1 / 2페이지일때, 6
		int endNavi;		// 각 페이지 끝 번호(Navi끝값)	1페이지일때, 5 /2페이지일때, 10
		
		// 23/5 = 4.8  이때, pageNavi의 최댓값(maxPage)은 5 이어야한다
		// 		  4.8 + 0.2 = 5.0
		//		  4.8 + 0.9 = 5.x
		// 42/5 = 8.x	이때, pageNavi의 최댓값은 9이어야한다
		// 		  8.x +0.9 = 9.x	따라서, 0.9를 항상 더해주면 된다
		// 							0.9를 더해주고, int로 형변환 해주면 소숫점이 다 날라간다
		maxPage = (int)((double)totalCount/boardLimit +0.9) ;	//totalCount: 총 게시물의 갯수
		
		//1페이지(currentPage=1)일때, -> 1~5	 startNavi =0 , endNavi =5 , boardLimit=5 
		//currentPage=2 , -> 6~10				 startNavi =1 , endNavi =10,  boardLimit=5 
		//currentPage=3,  -> 11~15				 startNavi =2, endNavi= 15, boardLimit=5
		startNavi =(((int)((double)currentPage/naviLimit+0.9))-1) *naviLimit +1;	//(((int)((double)currentPage/naviLimit+0.9))-1) :1,2,3,4,5 이면 0이되게 해주는 곳
		endNavi = startNavi + naviLimit -1 ;
		
		if(maxPage < endNavi) { //최대페이지 갯수 < 각 페이지 끝번호 -> 오류이다
			endNavi = maxPage;	//오른쪽에서 왼쪽으로 대입
		}
		
		//계산한 결과값을 pi에 담는다
		pi= new PageInfo(currentPage, boardLimit, naviLimit, startNavi, endNavi, totalCount, maxPage);		
		return pi;
	}
}
 