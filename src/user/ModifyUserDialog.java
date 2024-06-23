package user;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class ModifyUserDialog extends JDialog
{
    JLabel lUserId, lUserName;
    JTextField tfUserId, tfUserName;
    JButton btnModify;

    int userid;
    String strUserId;
    public ModifyUserDialog(String str, int userid)
    {
        this.userid = userid;
        strUserId = Integer.toString(userid);
        setTitle(str);
        init();
    }

    private void init()
    {
        lUserId = new JLabel("아이디");
        lUserName = new JLabel("이름");

        tfUserId = new JTextField(strUserId);
        tfUserId.setEditable(false);
        tfUserName = new JTextField(20);

        btnModify = new JButton("수정하기");
        btnModify.addActionListener(e ->
        {
            String userName = tfUserName.getText().trim();

            try
            {
                new UModify(userName, userid);
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }

            showMessage("회원 정보를 수정했습니다.");
        });

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(lUserId);
        panel.add(tfUserId);
        panel.add(lUserName);
        panel.add(tfUserName);

        add(panel, BorderLayout.CENTER);
        add(btnModify, BorderLayout.SOUTH);

        setLocation(400, 200);
        setSize(400, 300);
        setModal(true);
        setVisible(true);
    }

    private void showMessage(String msg)
    {
        JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
    }
}
