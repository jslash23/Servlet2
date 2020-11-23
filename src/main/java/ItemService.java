import java.io.IOException;

public class ItemService {

    ItemDAO itemDAO = new ItemDAO();

   public Item servRead() throws Exception {
        return itemDAO.daoRead();//
    }

    public void servSave(Item item) throws IOException {
         itemDAO.daoSave(item);//

    }

    public Item servUpdate() throws Exception {
        return itemDAO.daoUpdate();//
    }

    public Item servDelete(long idn) throws IOException{
        return itemDAO.daoDelete(idn);//
    }
}
