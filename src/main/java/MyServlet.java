import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.hibernate.dialect.OracleDialect;

@WebServlet(urlPatterns = "/test")

public class MyServlet extends HttpServlet {

    private static final ItemService itemService = new ItemService();

   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

     // метод должен возвращать в окно браузера введенное значени
       String params = req.getParameter("id");
       itemService.servRead(params);
       resp.getWriter().println(params);
   }

   @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Item item = new Item();
        //считываем стрим приходящий из Постмана (метод POST)
        //мапим данные Гибернейтом
        //добавляем ай ди и даты
        //сохраняем в БД

        item.setName(req.getParameter("name"));
        item.setDescription(req.getParameter("description"));
        itemService.servSave(item);
        resp.getWriter().println(item);
    }


    //делаем запрос req.getParameter("itemName") вызываем параметр  "itemName"
    // и по имени которое получили делаем обновление  объекта из БД
 /*  @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        long idServ = Long.parseLong(req.getParameter("id"));
        itemService.servUpdate(idServ);
    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long idServ = Long.parseLong(req.getParameter("id"));
        itemService.servDelete(idServ);

        //делаем запрос req.getParameter("itemId") вызываем параметр  "itemId"
        // и по айдишнику который получили делаем удаление объекта из БД
    }*/
}
