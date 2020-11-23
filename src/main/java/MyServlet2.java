
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import org.hibernate.dialect.OracleDialect;

@WebServlet(urlPatterns = "/test")

public class MyServlet2 extends HttpServlet  {
   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.getWriter().println("Test!!! Privet Use OK!!!!");
    }*/

    private static final ItemService itemService = new ItemService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.getWriter().println(req.getParameter("param"));//должен возвращать в окно браузера введенное значение
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        //считываем стрим приходящий из Постмана (метод POST)
        //мапим данные Гибернейтом
        //добавляем ай ди и даты
        //сохраняем в БД

        Item item = new Item();

        item.setName(req.getParameter("name"));
        item.setDescription(req.getParameter("description"));
       itemService.servSave(item);

        resp.getWriter().println("Post success");


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long idServ  = Long.parseLong(req.getParameter("id"));
        itemService.servDelete(idServ);

        //делаем запрос req.getParameter("itemId") вызываем параметр  "itemId"
        // и по айдишнику который получили делаем удаление объекта из БД
    }








}
