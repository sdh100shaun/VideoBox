package uk.ac.nottingham.confluence.plugins;

import com.atlassian.confluence.pages.Attachment;
import com.atlassian.confluence.pages.AttachmentDataExistsException;
import com.atlassian.confluence.pages.AttachmentManager;

import java.io.InputStream;

/**
 * uk.ac.nottingham.confluence.plugins
 *
 * @version 1.0
 * @author: shaunhare
 * Date: 07/07/2011
 * Time: 19:54
 */
public class Player {


    public InputStream getPlayer() {
        return player;
    }

    public void setPlayer(InputStream player) {
        this.player = player;
    }

    public InputStream player;


    public Player()
    {

    }

    public void setPlayerAsAttachment(AttachmentManager attachmentManager)
    {

        try{
            Attachment attachment = new Attachment();
            attachment.setFileName("player.swf");
            attachmentManager.setAttachmentData(attachment,player);

        } catch (AttachmentDataExistsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
