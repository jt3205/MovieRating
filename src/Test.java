import info.InfoDAO;
import info.InfoVO;

public class Test {
	public static void main(String[] args) {
		InfoVO vo = new InfoDAO().getInfo("�ýÿ�����");
		System.out.println(vo.getEnglishTitle());
	}
}
