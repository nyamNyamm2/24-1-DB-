package recipe;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class ModifyRecipeDialog extends JDialog
{
    JLabel lTitle, lId, lContent, lDiff;
    JTextField tfTitle, tfId, tfContent, tfDiff;
    JButton btnModify;

    String title;

    public ModifyRecipeDialog(String str, String title)
    {
        this.title = title;
        setTitle(title);
        init();
    }

    private void init()
    {
        lTitle = new JLabel("레시피 이름");
        lId = new JLabel("아이디");
        lContent = new JLabel("설명");
        lDiff = new JLabel("난이도(*)");

        tfTitle = new JTextField(title);
        tfTitle.setEditable(false);
        tfId = new JTextField(20);
        tfContent = new JTextField(20);
        tfDiff = new JTextField(20);

        btnModify = new JButton("수정하기");
        btnModify.addActionListener(e ->
        {
            int id = Integer.parseInt(tfId.getText().trim());
            String content = tfContent.getText().trim();
            String diff = tfDiff.getText().trim();

            try
            {
                new RModify(title, id, content, diff);
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }

            showMessage("레시피 정보를 수정했습니다.");
        });

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(lTitle);
        panel.add(tfTitle);
        panel.add(lId);
        panel.add(tfId);
        panel.add(lContent);
        panel.add(tfContent);
        panel.add(lDiff);
        panel.add(tfDiff);

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
