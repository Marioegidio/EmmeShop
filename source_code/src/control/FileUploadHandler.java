package control;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import model.UserModelDS;
 
/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */


@WebServlet("/upload")
public class FileUploadHandler extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String UPLOAD_DIRECTORY = "C:\\Users\\Mario\\Desktop\\TSW\\project\\emmeShop\\WebContent\\images\\negozi";
       
    	HttpSession session=request.getSession();
		String nomeNegozio=(String) session.getAttribute("NomeNegozio");
		String urlLogo="";
		UPLOAD_DIRECTORY+="\\"+nomeNegozio;
		UserModelDS model=new UserModelDS();
		if(!(new File(UPLOAD_DIRECTORY)).exists())
			new File(UPLOAD_DIRECTORY).mkdir();

        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
        	 try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest( new ServletRequestContext(request));
               
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        int index = name.indexOf(".");
                        String estensione= name.substring(index);
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + nomeNegozio+estensione));
                        urlLogo="images/negozi/"+nomeNegozio+"/"+nomeNegozio+estensione;
                        session.setAttribute("urlLogoNegozio", urlLogo);
                        model.updateLogoNegozio(nomeNegozio,urlLogo);
                    }
                }
                model.updateLogoNegozio(nomeNegozio,urlLogo);
               //File uploaded successfully
               //request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
               //request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
          
        }else{
            //request.setAttribute("message","Sorry this Servlet only handles file upload request");
        }
     
        //request.getRequestDispatcher("./seller/uploadImage.jsp").forward(request, response);
        response.sendRedirect("./seller/uploadImage.jsp");
    }
   
}
