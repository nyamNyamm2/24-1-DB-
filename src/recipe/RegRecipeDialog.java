package recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegRecipeDialog extends JDialog
{
    JPanel jPanel;
    JLabel lTitle, lId, lContent, lDiff;
    JTextField tfTitle, tfId, tfContent, tfDiff;
    JButton btnReg;
    
    public RegRecipeDialog (String str)
    {
        setTitle(str);
        init();
    }
    
    private void init()
    {
        lTitle = new JLabel("레시피 이름");
        tfTitle = new JTextField(20);
        lId = new JLabel("아이디");
        tfId = new JTextField(20);
        lContent = new JLabel("설명");
        tfContent = new JTextField(20);
        lDiff = new JLabel("난이도(*)");
        tfDiff = new JTextField(20);

        btnReg=new JButton("레시피 등록");
        btnReg.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String title=tfTitle.getText().trim();
                int id=Integer.parseInt(tfId.getText().trim());
                String  content=tfContent.getText().trim();
                String diff=tfDiff.getText().trim();
                try
                {
                    new RInsert(title, id, content, diff);
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }

                showMessage("새 레시피를 등록했습니다.");
                tfTitle.setText("");
                tfId.setText("");
                tfContent.setText("");
                tfDiff.setText("");
            }
        });

        jPanel=new JPanel(new GridLayout(0,2));

        jPanel.add(lTitle);
        jPanel.add(tfTitle);
        jPanel.add(lId);
        jPanel.add(tfId);
        jPanel.add(lContent);
        jPanel.add(tfContent);
        jPanel.add(lDiff);
        jPanel.add(tfDiff);

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
