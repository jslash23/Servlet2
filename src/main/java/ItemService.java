import java.io.IOException;

public class ItemService {



   public Item servRead( String desc) throws Exception {
       ItemDAO itemDAO = new ItemDAO();
        return itemDAO.daoRead(desc);//
    }

  /*  public void servSave(Item item) throws IOException {
        ItemDAO itemDAO = new ItemDAO();
         itemDAO.daoSave(item);//

    }*/

    public void servUpdate(long id) throws IOException {
        ItemDAO itemDAO = new ItemDAO();
         itemDAO.daoUpdate(id);//
    }

  /*  public void servDelete(long idn) throws IOException{
        ItemDAO itemDAO = new ItemDAO();
         itemDAO.daoDelete(idn);//
    }*/
}
