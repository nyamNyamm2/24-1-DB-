package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegUserDialog extends JDialog
{
    JPanel jPanel;
    JLabel lName;
    JTextField tfName;
    JButton btnReg;

    public RegUserDialog(String str)
    {
        setTitle(str);
        init();
    }

    private void init()
    {
        lName= new JLabel("이름");
        tfName=new JTextField(20);

        btnReg=new JButton("회원 등록");
        btnReg.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name=tfName.getText().trim();
                try
                {
                    new UInsert(name);
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }

                showMessage("새 회원을 등록했습니다.");
                tfName.setText("");
            }
        });

        jPanel=new JPanel(new GridLayout(0,2));

        jPanel.add(lName);
        jPanel.add(tfName);

        add(jPanel,BorderLayout.NORTH);
        add(btnReg,BorderLayout.SOUTH);

        setLocation(400, 200);
        setSize(400,400);
        setModal(true);
        setVisible(true);
    }

    private void showMessage(String msg)
    {
        JOptionPane.showMessageDialog(this, msg, "메시지박스", JOptionPane.INFORMATION_MESSAGE);
    }
}
