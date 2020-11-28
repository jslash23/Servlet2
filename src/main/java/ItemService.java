import java.io.IOException;

public class ItemService {


    ItemDAO itemDAO = new ItemDAO();

    public void servRead(String id) throws IOException {

        itemDAO.daoRead(id);//
    }

    public void servSave(Item item) throws IOException {
        itemDAO.daoSave(item);//

    }

    public void servUpdate(long id) throws IOException {

        itemDAO.daoUpdate(id);//
    }

    public void servDelete(long idn) throws IOException {

        itemDAO.daoDelete(idn);//
    }
}
