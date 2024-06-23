package recipe;

import search.SInsert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CheckUser extends JDialog
{
    public CheckUser(String title, String content)
    {
        JButton btnIn;
        JLabel lUser = new JLabel("현재 사용자 아이디");
        JTextField tfUser = new JTextField(20);

        btnIn = new JButton("레시피 설명 보기");
        btnIn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int id = Integer.parseInt(tfUser.getText().trim());

                try
                {
                    new SInsert(id, title);
                    new ViewContent(title, content);
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(lUser);
        panel.add(tfUser);
        add(panel, BorderLayout.CENTER);
        add(btnIn, BorderLayout.SOUTH);

        setLocation(400, 200);
        setSize(350, 100);
        setModal(true);
        setVisible(true);
    }
}
