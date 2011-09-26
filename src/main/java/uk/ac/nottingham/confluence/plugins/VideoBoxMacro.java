package uk.ac.nottingham.confluence.plugins;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.text.ParseException;

import com.atlassian.confluence.pages.*;
import org.apache.velocity.VelocityContext;

import com.atlassian.renderer.RenderContext;
import com.atlassian.renderer.links.GenericLinkParser;
import com.atlassian.renderer.v2.macro.BaseMacro;
import com.atlassian.renderer.v2.macro.MacroException;
import com.atlassian.renderer.v2.RenderMode;
import com.atlassian.confluence.core.ContentEntityObject;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.renderer.PageContext;
import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.confluence.spaces.SpaceManager;
import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.confluence.util.velocity.VelocityUtils;


/**
 * This very simple macro shows you the very basic use-case of displaying *something* on the Confluence page where it is used.
 * Use this example macro to toy around, and then quickly move on to the next example - this macro doesn't
 * really show you all the fun stuff you can do with Confluence.
 */
public class VideoBoxMacro extends BaseMacro
{

    // We just have to define the variables and the setters, then Spring injects the correct objects for us to use. Simple and efficient.
    // You just need to know *what* you want to inject and use.

    private final PageManager pageManager;
    private final SpaceManager spaceManager;
    private final SettingsManager settingsManager;
    private final AttachmentManager attachmentManager;

    private static String macroBodyTemplate="templates/videobox-macro.vm"; 
    
    public VideoBoxMacro(PageManager pageManager,SettingsManager settingsManager, SpaceManager spaceManager,AttachmentManager attachmentManager)
    {
        this.pageManager = pageManager;
        this.spaceManager = spaceManager;
        this.settingsManager=settingsManager;
        this.attachmentManager= attachmentManager;

    }

    public boolean isInline()
    {
        return false;
    }

    public boolean hasBody()
    {
        return false;
    }

    public RenderMode getBodyRenderMode()
    {
        return RenderMode.NO_RENDER;
    }

    /**
     * Main Execute method for videobox
     */
    public String execute(Map params, String body, RenderContext renderContext)
            throws MacroException
    {
    	String movie = null; 
    	String height = null; 
    	String width = null; 
    	String staticImage = null; 
    	String image = null;
    	Map<String,Object> velocityContext = MacroUtils.defaultVelocityContext();

        ContentEntityObject contentObject = ((PageContext) renderContext).getEntity();



    	String baseUrl = settingsManager.getGlobalSettings().getBaseUrl(); 
    	
        if (params.containsKey("url")  && params.get("url")!= null)
        {
        	movie = params.get("url").toString();

            if(!movie.contains("http"))
            {
                movie= baseUrl+attachmentManager.getAttachment(contentObject,movie).getDownloadPath();
            }

        }

        if (params.containsKey("image")  && params.get("image")!= null)
        {

            image = params.get("image").toString();
            if(!image.contains("http"))
            {
              image= baseUrl+attachmentManager.getAttachment(contentObject,image).getDownloadPath();
            }



        }
        if (params.containsKey("height")  && params.get("height")!= null)
        {
        	height = params.get("height").toString();
            if(height.contains("px"))
            {
                height=height.substring(0,height.lastIndexOf("p"));
            }
            


        }
        
        if (params.containsKey("width")  && params.get("width")!= null)
        {
        	width = params.get("width").toString();
            if(width.contains("px"))
               {
                   width=width.substring(0,width.lastIndexOf("p"));
               }

        }
        
        if(params.containsKey("firstframe") && params.get("firstframe")!=null)
        {
        	staticImage= params.get("firstframe").toString();



            staticImage= baseUrl+attachmentManager.getAttachment(contentObject,staticImage).getDownloadPath();
        }
        
        velocityContext.put("movie", new Movie(movie, height, width,staticImage ));
        velocityContext.put("baseUrl", baseUrl);
        velocityContext.put("image",image);


         Player player = new Player();
         player.setPlayerAsAttachment(attachmentManager);


        return VelocityUtils.getRenderedTemplate(macroBodyTemplate, velocityContext);
    }

}