package recipe;

import java.awt.*;
import javax.swing.*;

public class ViewContent extends JDialog
{
    JTextField jTitle;
    JTextArea jContent;

    String title, content;

    public ViewContent(String title, String content)
    {
        this.title = title;
        this.content = content;
        init();
    }

    private void init()
    {
        jTitle = new JTextField(title);
        jTitle.setEditable(false);
        jContent = new JTextArea(content, 50, 20);
        jContent.setEditable(false);


        add(jContent, BorderLayout.CENTER);
        add(jTitle, BorderLayout.NORTH);

        setLocation(400, 200);
        setSize(400, 300);
        setModal(true);
        setVisible(true);
    }
}
