package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {
	// DAO (Data Access Object) 데이터 처리 객체
	// DB 쓰는 작업들 여기서 다,,,
	
	// 공통 변수 선언 (인스턴스 변수)
	private Connection con = null; 			// DB 연결 정보 저장하는 객체
	private PreparedStatement pstmt = null; // DB에 sql 실행 처리해주는 객체
	private ResultSet rs = null; 			// select 실행 결과를 저장하는 객체
	private String sql = ""; 				// sql 쿼리 구문 저장하는 객체
	
	// 기본 생성자
	public BoardDAO() {
		System.out.println("(from BoardDAO) DB 연결에 관한 모든 준비 완^^ ");
	}
	
	
	// DB 연결 메서드
/*	private Connection getConnect() throws Exception{
		// 디비 연결정보
		String DRIVER = "com.mysql.cj.jdbc.Driver";
		String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		String DBID = "root";
		String DBPW = "1234";
		
		// 1. 드라이버로드
		Class.forName(DRIVER);
		System.out.println("(from BoardDAO_getConnect) 드라이버로드 성공 ");
		
		// 2. 디비연결
		con = DriverManager.getConnection(DBURL, DBID, DBPW);
		System.out.println("(from BoardDAO_getConnect) 디비연결 성공 ");
		System.out.println("(from BoardDAO_getConnect) con : " + con);
		
		return con;
	}*/
	// 디비 연결 끝///////////////////////////////////////////////////////////////
	
	
	// DB 연결 메서드2 커넥션풀 이용!!!!!!!!!
	private Connection getConnect() throws Exception{
		// 디비 연결정보 context.xml
						/*<Context>
						<Resource 
							name="jdbc/model2" 
							auth="Container" 
							type="javax.sql.DataSource" 
							driverClassName="com.mysql.cj.jdbc.Driver" 
							username="root" 
							password="1234" 
							url="jdbc:mysql://localhost:3306/jspdb" 
							maxWait="5000" 
						/>
					</Context>*/
		
		// 프로젝트 정보를 초기화
		Context initCTX = new InitialContext(); // upcasting 상 = 하
				// 초기화된 context
				// 프젝 초기화 한번 해서 initCTX에 넣겠다..
		// 초기화된 프로젝트 정보 중에서 데이터 불러오기!! + ds에 저장 + 다운캐스팅까지
								// DB 연결 정보 불러오면 되겠넴
//		initCTX.lookup("java:comp/env"); // 여까지는 고정
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/model2"); 
																// name="jdbc/model2"
		// 연결된 정보를 바탕으로 connection 정보를 리턴
		con = ds.getConnection();
		
		return con;
	}
	// 디비 연결 끝///////////////////////////////////////////////////////////////
	
	
	// 자원 해제
	public void closeDB(){
		//con -> pstmt -> rs 순으로 만들어지니까 해제는 역순으로
		// rs -> pstmt -> con !!!!!!
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
			System.out.println("(from BoardDAO_closeDB) 자원 해제 완 ㅂ2ㅂ2");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	// 자원 해제 끝//////////////////////////////////////////////////////////////
	
	
	// 1. 글쓰기 메서드 boardWrite()
	public void boardWrite(BoardDTO dto){
		System.out.println("\n (from BoardDAO_1.boardWrite) C: boardWrite() 호출");

		// dto에 글쓴 정보 저장되어 있으니까,, 그 dto를 매개변수로 받아오기
		int bno = 0; // 글 번호 저장할 변수 준비
		try {
			// 1+2. 드라이버로드 + DB 연결 + 6. 자원 해제까지,,
			con = getConnect();
			
			// 3. sql & pstmt & ?
			//		근데,, dto에서 모든 컬럼들 다 받아온 게 아닌데,???? 우야지? 
			//      nn 안 걸린 애들은 ㄱㅊ한데,, pk인 bno도 없음.. 클났네~~
			// 		bno를 여기서 만들고 가실게요~~~~~~~ 
			// 게시판 글 번호(bno) 계산 (= 작성된 가장 마지막 번호 + 1)
					// 기존에 있는 글 개수 + 1 = 새로 작성하려는 글 번호넴
					// 방법 2가지 == 1. 직접 계산해서 번호 매김 / 2. 자동으로~~
			sql = "select max(bno) from itwill_board"; // bno 컬럼의 최댓값을 갖고 와봐~~ 
				// count(bno)는?? 될 수도 안 될 수도,, 왜냐면 중간에 글 삭제한 거 있으면,, 중복이 생겨버림~ pk인데 중복되면 안돼~~~
			pstmt = con.prepareStatement(sql);
			
			// 4. sql 실행
			rs = pstmt.executeQuery(); // select 구문이니까 rs에 저장
			
			// 5. 데이터 처리 (글 번호 계산)
			// rs에 있는 데이터,, 처리
			if(rs.next()){
				// max(bno)가 있을 때~~ +1 하면 끝
//				bno = rs.getInt("bno") + 1; // 땡~~~~~~ X 
//				bno = rs.getInt("max(bno)") + 1; // 딩동댕~~~ O
				bno = rs.getInt(1) + 1; // 딩동댕~~~ OOO 1번 컬럼=인덱스에 있는 값!!! 
			} /* else {
				// bno가 없을 때,, = 아무것도 없을 때는~~
				// bno = 1;
				// 근데 else 구문 필요 없삼~~ getInt + f2 눌러서,, 메서드 소개글에 보면,,,
				// Returns: the column value; if the value is SQL NULL, the value returned is 0
				// null일 때 -> 0 리턴!!!!!!
				
				// 그럼 else 언제 쓰냐,,,? 굳이 안 써도 됨,,,,, 하하하,,
				// max(bno)         =/=     bno  달라요~~~~~~~~~~~
				//   ▶                       ●
				// 커서가 있고              없고..
				// rs.next() == true         rs.next() == false
				// 
				
			} */
			
			System.out.println("(from BoardDAO_1.boardWrite) 글번호 bno: " + bno);
			// 글 번호 계산 끝났고,,
			// 게시판 글쓰기 실행
			// 다시 3단계. sql 작성 & pstmt & ?
			sql = "insert into itwill_board(bno, name, pass, subject, content,"
					+ "readcount, re_ref, re_lev, re_seq, date, ip, file) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
													// date는 직접 지정 안 하고 now로 할게요,,,,,^^,, 
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, 0); // 게시판에 글 처음 딱 쓰면 조회수는 항상 0
			pstmt.setInt(7, bno); // 답글 그룹 번호 == 글 번호 (일반글)
			pstmt.setInt(8, 0); // 답글 레벨 0 (일반글)
			pstmt.setInt(9, 0); // 답글 순서 0 (일반글)
						// 10번째 컬럼 date는 now()로 채웠으니까
			pstmt.setString(10, dto.getIp());
			pstmt.setString(11, dto.getFile());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			System.out.println("(from BoardDAO_boardWrite) 글 작성 완  bno: " + bno);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			closeDB();
		}
		
	}
	// 1. 글쓰기 메서드 boardWrite() 끝///////////////////////////////////////////////////
	
	
	// 2. 글 목록 조회(all) - getBoardList()
	public List<BoardDTO> getBoardList(){
		System.out.println("\n(from BoardDAO_2.getBoardList) C: getBoardList() 호출");

		// 글 정보 모~~~두를 저장하는 배열 (가변길이!! 고정길이 배열 BoardDTO[] 말고)
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			// 1+2
			con = getConnect();

			// 3 sql & pstmt & ?
			sql = "select * from itwill_board";
			
			pstmt = con.prepareStatement(sql);
			
			
			// 4 sql 실행 & 날려서 가져온 결과값 rs에 담기
			rs = pstmt.executeQuery();
			
			// 5 데이터 처리
			while(rs.next()){
				// rs에 데이터 결과값 RecordSet 있을 때!! 이걸 화면에 출력해줘야 하는데 어떡한담~~
				// 이 정보를 바로 배열로는 못 넣고,, DB에 저장된 정보 -> DTO에 저장 -> List에 저장 
				// ArrayList에 ㅁㅁㅁㅁㅁ
				//              ㄴㄴㄴㄴㄴ DTO 필통에 저장 먼저!!!
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt(1));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString(3));
				dto.setSubject(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRe_ref(rs.getInt(7));
				dto.setRe_lev(rs.getInt(8));
				dto.setRe_seq(rs.getInt(9));
				dto.setDate(rs.getDate(10));
				dto.setIp(rs.getString("ip"));
				dto.setFile(rs.getString("file"));
				
				// DTO필통 -> List에 저장쓰
				boardList.add(dto);
				
			} // while
			System.out.println("(from BoardDAO_2.getBoardList) C: List에 저장 완");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 6
			closeDB();
		}
		
		return boardList;
		
	}	
	// 2. 글 목록 조회(all) - getBoardList() 끝///////////////////////////////////////////
	
	
	// 2-1. 글 목록 조회(all 아니고 내가 원하는 만큼만) - getBoardList(startRow, pageSize) 오버로딩!!!!!
	public List<BoardDTO> getBoardList(int startRow, int pageSize){
		System.out.println("\n(from BoardDAO_2-1.getBoardList) C: getBoardList(sr, ps) 호출");
		
		// 글 정보 모~~~두를 저장하는 배열 (가변길이!! 고정길이 배열 BoardDTO[] 말고)
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			// 1+2
			con = getConnect();
			
			// 3 sql & pstmt & ?
//			sql = "select * from itwill_board"; (x)
			
			// limit 시작행-1, 들고 올 개수 : 시작 지점부터 해당 개수만큼 짤라오기!!!
			// 정렬: re_ref 이용해서 내림차순으로 정렬.. re_ref = bno = 큰-> 작..  = 최신 -> 구
			//     : re_seq 이용해서 오름차순으로 정렬 1, 2, 3...
			//   ㄴ 최신글이 제일 위에 오도록!!!! 모르겠으면 쿼리 날려서 직접 보삼
			sql =     "select * from itwill_board "
					+ "order by re_ref desc, re_seq asc "
					+ "limit ?, ?";
			
			pstmt = con.prepareStatement(sql);
			
			// ? 처리
			pstmt.setInt(1, startRow-1); // DB에서 인덱스 0부터 시작하니까,, -1 --> 시작행-1
			pstmt.setInt(2, pageSize); // 몇 개씩 보여줄거? 매개변수로 받아온 pageSize만큼
			// 이 밑에부터는 똑같음~~~~~~~~~
			
			// 4 sql 실행 & 날려서 가져온 결과값 rs에 담기
			rs = pstmt.executeQuery();
			
			// 5 데이터 처리
			while(rs.next()){
				// rs에 데이터 결과값 RecordSet 있을 때!! 이걸 화면에 출력해줘야 하는데 어떡한담~~
				// 이 정보를 바로 배열로는 못 넣고,, DB에 저장된 정보 -> DTO에 저장 -> List에 저장 
				// ArrayList에 ㅁㅁㅁㅁㅁ
				//              ㄴㄴㄴㄴㄴ DTO 필통에 저장 먼저!!!
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt(1));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString(3));
				dto.setSubject(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRe_ref(rs.getInt(7));
				dto.setRe_lev(rs.getInt(8));
				dto.setRe_seq(rs.getInt(9));
				dto.setDate(rs.getDate(10));
				dto.setIp(rs.getString("ip"));
				dto.setFile(rs.getString("file"));
				
				// DTO필통 -> List에 저장쓰
				boardList.add(dto);
				
			} // while
			System.out.println("(from BoardDAO_2-1.getBoardList) C: List에 저장 완");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			// 6
			closeDB();
		}
		
		return boardList;
		
	}	
	// 2-1. 글 목록 조회(all 아니고 내가 원하는 만큼만) - getBoardList(startRow, pageSize) 끝//////////////////////////////////////
	
	
	// 3. 글 개수 조회(all) - getBoardCount()
	public int getBoardCount(){
		System.out.println("\n(from BoardDAO_3.getBoardCount) C: getBoardCount() 호출");
		int cnt = 0;
		
		try {
			// 1+2. 디비 연결(커넥션 풀 이용) + 6. closeDB
			con = getConnect();
			
			// 3. sql 작성 & pstmt & ?
			sql = "select count(*) from itwill_board";
			pstmt = con.prepareStatement(sql);
			
			// 4. sql 실행 + rs
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리 (select날리니까)
			if(rs.next()){ // rs에 데이터가 있을 때~
				cnt = rs.getInt(1); // 1번 컬럼(=count(*)) 값을 cnt에 저장쓰
			}
			
			System.out.println("(from BoardDAO_3.getBoardCount) C: 글 개수는 총 " + cnt + "개^^");
			
		} catch (Exception e) { 
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return cnt;
	}
	// 3. 글 개수 조회(all) - getBoardCount() 끝///////////////////////////////////////////
	
	
	// 4. 글 조회수 1 증가 updateReadcount(bno)
	public void updateReadcount(int bno){
		System.out.println("(from BoardDAO_4.updateReadcount) C: updateReadcount(int bno) 호출 완");
		
		try {
			// 1+2.    + 6. 자원해제 미리
			con = getConnect();
			
			// 3. sql & pstmt & ?
			sql =    "update itwill_board "
					+ "set readcount = readcount+1 "
					+ "where bno=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("(from BoardDAO_4.updateReadcount) C: 게시판 글 조회수 +1 완");
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			closeDB();
		}

	}
	// 4. 글 조회수 1 증가 updateReadcount(bno) 끝 //////////////////////////////////////////////////////////
	
	
	// 5. 특정 글 1개의 정보 조회 getBoard(bno)
	public BoardDTO getBoard(int bno){
		System.out.println("(from BoardDAO_5.getBoard) C: getBoard(bno) 호출");

		BoardDTO dto = null; // 여기서  new XXXX!!!!!  필요할 때 생성할 수 있도록,, 여기선 null로만
		
		try {
			// 1+2.    +6.
			con = getConnect();
			
			// 3.
			sql = "select * from itwill_board where bno=?"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			// 4. 
			rs = pstmt.executeQuery();
			
			// 5.
			
			if(rs.next()){ // 데이터 결과값: 하나니까 if
				// rs에 담겨 있는 DB 데이터 -> DTO에 넣기
				dto = new BoardDTO(); // 인제 new 하기1!!!
				
				// bno name pass subject content / readcount re_ref re_lev re_seq / date ip file
				//  1                                 6                              10
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString(3));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getInt(6));
				dto.setRe_ref(rs.getInt(7));
				dto.setRe_lev(rs.getInt(8));
				dto.setRe_seq(rs.getInt(9));
				dto.setDate(rs.getDate(10));
				dto.setIp(rs.getString(11));
				dto.setFile(rs.getString(12));
				// 근데,, 굳이 얘넬 다 들고 가야하나? 필요한 애들만 몇 개 들고가면 안 되나~~~
				// 넵,, 전체 다 들고오는 메서드 필수!!! 거기서 인제 나아가서~ 필요한 몇 개만 추려서 또 만들고,, 그러나 봄
				
			}// if
			
			System.out.println("(from BoardDAO_5.getBoard) C: 게시글 " + bno + "번 정보 dto에 저장 완");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return dto;
	}
	// 5. 특정 글 1개의 정보 조회 getBoard(bno) 끝 //////////////////////////////////////////////////////////
	
	
	// 6. 글 정보 수정 updateBoard(dto) 메서드 시작 ////////////////////////////////////////////////////////
	public int updateBoard(BoardDTO dto){
		System.out.println("(from BoardDAO_6.updateBoard) C: updateBoard(dto) 호출");
		int result = -1;
		// result = 1 본인 인증 완 -> 글 수정 성공 
		//        = 0 비번 틀림,, 본인 X
		//        = -1 게시판에 글 없다 ㄷㄷㄷ
		
		try {
			// 1. 2. DB 연결   + 6. closeDB
			con = getConnect();
			
			// 3. sql 작성 & pstmt & ?
			sql = "select pass from itwill_board where bno=?";
						// pk가 걸려있는 bno에 해당하는 pass 가 있다면,, 글은 존재하는 거다~~ 
						// bno에 해당하는 pass가 없다? 비번 틀린 거? ㄴㄴ
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBno());
			
			// 4. sql 실행 & rs에 담기
			rs = pstmt.executeQuery();
			
			// 5. rs에 담긴 데이터 처리
			if(rs.next()){
				// 데이터 있을 때~ 
				// 비번 다시 확인
				if(dto.getPass().equals(rs.getString("pass"))){
					// db에서 가져온(rs에 담긴) pass랑 -- dto에 있는 pass랑 비교
					// 비번 같으면~ == 게시판에 글도 있고, 비번도 맞더라~~ == 본인이 쓴 글 맞다~~
					// 여기서!!!!! 수정 가능하게~~~~~~~~~~~~~~~~~~
					// 3. sql & pstmt & ?
					sql = "update itwill_board "
							+ "set name=?, subject=?, content=? "
							+ "where bno=? ";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getName());
					pstmt.setString(2, dto.getSubject());
					pstmt.setString(3, dto.getContent());
					pstmt.setInt(4, dto.getBno());
					
					// 4. sql 실행
					result = pstmt.executeUpdate(); 
								// executeUpdate 실행하고 나면 정수형 데이터(쿼리 날린 결과, 몇 row가 영향을 받았냐) 리턴함!!! 
								// 근데 여기선 무조건 1row니까~ 1이 리턴되는고 
				} else {
					// 비번 다름
					result = 0;
				}// 안에 if-else

			} else {
				// bno에 해당하는 비번이 없다~~ = 게시판에 글이 없다~~~
				result = -1;
			}
			System.out.println("(from BoardDAO_6.updateBoard) 글 수정 완 result: " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			closeDB();
		}
		
		return result;
	}
	// 6. 글 정보 수정 updateBoard(dto) 메서드 끝 ////////////////////////////////////////////////////////
	
	
	
	
	// self //////////////////////////////////////////////
	// 7. 답글 쓰기 메서드 reInsertBoard(dto)
	public void reInsertBoard(BoardDTO dto) {
		System.out.println("(from BoardDAO_7.reInsertBoard) 답글 쓰기 메서드 호출됨");
		
		int result = -1;
		
		// 1.지역변수생성
		int num = 0;
		
		// 2. 디비연결
		try {
			getConnect();
		
			// sql(게시판의 글번호 중 최댓값 계산) & pstmt
			
			// 3. 답글번호 계산
			sql = "select max(bno) from itwill_board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			System.out.println("(from BoardDAO_7.reInsertBoard) 답글 번호 : " + num);

			// 4. 답글 순서 재배치
			// re_ref(같은 그룹기준)으로 re_seq값이 기존의 값보다 큰 값이 있을경우 re_seq값을 1증가시켜서
			// 순서재배치
			sql = "update itwill_board "
					+ "set re_seq = re_seq+1 " 
					+ "where re_ref=? and re_seq>?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getRe_ref());
			pstmt.setInt(2, dto.getRe_seq());
			
			pstmt.executeUpdate();

			// 5. 답글 정보 저장
			sql = "insert into itwill_board " + "values(?,?,?,?,?" + ",?,?,?,?,now()" + ",?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);// bno는 가지고있는게 아니라 계산해서 만든것이므로 num사용
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, dto.getReadcount());
			pstmt.setInt(7, dto.getRe_ref()); // 기존 원글의 그룹번호와 동일
			pstmt.setInt(8, dto.getRe_lev() + 1); // 기존의 값 + 1
			pstmt.setInt(9, dto.getRe_seq() + 1); // 기존의 값 + 1
			pstmt.setString(10, dto.getFile());
			pstmt.setString(11, dto.getIp());
			
			pstmt.executeUpdate();
			
			result = 1;

			System.out.println("(from BoardDAO_7.reInsertBoard): 저장된 답글 내용: " + dto);
			System.out.println("(from BoardDAO_7.reInsertBoard): 답글쓰기 성공 result: " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			closeDB();
		}
	}
	// 7. 답글 쓰기 메서드 reInsertBoard(dto) 끝
	
	
	
	
	
	
}// BoardDAO class
