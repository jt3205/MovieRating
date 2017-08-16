import info.InfoDAO;
import info.InfoVO;

public class Test {
	public static void main(String[] args) {
		InfoVO vo = new InfoDAO().getInfo("택시운전사");
		System.out.println(vo.getEnglishTitle());
	}
}
