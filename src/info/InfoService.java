package info;

public class InfoService {
	InfoDAO dao = new InfoDAO();
	private void insertInfo(InfoVO[] vo) {
		dao.insertInfo(vo);
	}
}
