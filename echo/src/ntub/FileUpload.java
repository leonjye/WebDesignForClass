package ntub;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/upload")
@MultipartConfig
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name"); // Retrieves <input type="text" name="name">
	    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    InputStream in = filePart.getInputStream();
	    byte[] buffer = new byte[in.available()];
	    in.read(buffer);
	    File targetFile = new File(fileName);
	    if(!targetFile.exists())
	    	targetFile.createNewFile();
	    try(OutputStream outStream = new FileOutputStream(targetFile)){
	    	outStream.write(buffer);
	    }
	    response.getWriter().append("receive file: " + fileName + "\n");
	    response.getWriter().append("save in " + targetFile.getAbsolutePath() + "\n");
	    response.getWriter().append("name: " + name);
	}

}
