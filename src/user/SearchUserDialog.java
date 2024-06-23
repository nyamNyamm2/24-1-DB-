package user;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import abs.*;

public class SearchUserDialog extends JDialog
{
    JPanel panelSearch, panelBtn;
    JLabel lName;
    JTextField tf;
    JButton btnSearch;
    JButton btnModify;
    JButton btnDelete;

    JTable userTable;
    String[] columnNames = { "아이디", "이름"};
    RentTableModel rentTableModel;

    Object[][] memItems = new String[0][2];
    int rowIdx = 0, colIdx = 0;

    public SearchUserDialog(String str)
    {
        setTitle(str);
        init();
    }

    private void init()
    {
        userTable = new JTable();
        ListSelectionModel rowSel = userTable.getSelectionModel();
        rowSel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSel.addListSelectionListener(new ListRowSelectionHandler());

        ListSelectionModel colSel = userTable.getColumnModel().getSelectionModel();
        colSel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colSel.addListSelectionListener(new ListColSelectionHandler());

        panelSearch = new JPanel();
        panelBtn = new JPanel();

        btnSearch = new JButton("조회하기");
        btnSearch.addActionListener(new MemberBtnHandler());

        panelSearch.add(btnSearch);

        btnModify = new JButton("수정하기");
        btnDelete = new JButton("삭제하기");

        btnModify.addActionListener(new MemberBtnHandler());
        btnDelete.addActionListener(new MemberBtnHandler());

        panelBtn.add(btnModify);
        panelBtn.add(btnDelete);

        add(panelSearch, BorderLayout.NORTH);
        add(panelBtn, BorderLayout.SOUTH);

        rentTableModel = new RentTableModel(memItems, columnNames);
        userTable.setModel(rentTableModel);
        add(new JScrollPane(userTable), BorderLayout.CENTER);

        setLocation(300, 100);
        setSize(600, 600);
        setModal(true);
        setVisible(true);

    }

    class MemberBtnHandler implements ActionListener
    {
        int userId = 0;
        String userName = null;
        List<UserVO> userList = null;

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == btnSearch)
            {
                UView userview = new UView();
                try
                {
                    userList = userview.view();
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
                loadTableData(userList);
                return;
            }
            else if (e.getSource() == btnDelete)
            {
                userId = Integer.parseInt((String) memItems[rowIdx][0]);
                userName = (String) memItems[rowIdx][1];
                UserVO userVO = new UserVO(userId, userName);

                try
                {
                    new UDelete(userVO);
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
                showMessage("회원 정보를 삭제했습니다.");
            }
            else if (e.getSource() == btnModify)
            {
                userId = Integer.parseInt((String) memItems[rowIdx][0]);
                userName = (String) memItems[rowIdx][1];
                UserVO userVO = new UserVO(userId, userName);

                new ModifyUserDialog("회원 수정 창", userId);

                UView userview = new UView();
                try
                {
                    userList = userview.view();
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
                loadTableData(userList);
            }

            List<UserVO> userList = new ArrayList<UserVO>();
            UserVO memVO = new UserVO();
            UView userview = new UView();
            try
            {
                userList = userview.view();
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
            loadTableData(userList);
        }
    }

    private void loadTableData(List<UserVO> userList)
    {
        if (userList != null && userList.size() != 0)
        {
            memItems = new String[userList.size()][2];
            for (int i = 0; i < userList.size(); i++)
            {
                UserVO userVO = userList.get(i);
                memItems[i][0] = Integer.toString(userVO.getUserId());
                memItems[i][1] = userVO.getUserName();
            }

            rentTableModel = new RentTableModel(memItems, columnNames);
            userTable.setModel(rentTableModel);
        } 
        else 
        {
            message("조회한 정보가 없습니다.");
            memItems = new Object[10][10];
            rentTableModel = new RentTableModel(memItems, columnNames);
            userTable.setModel(rentTableModel);
        }
    }

    private void message(String msg) 
    {
        JOptionPane.showMessageDialog(this, msg, "메시지박스", JOptionPane.INFORMATION_MESSAGE);
    }

    class ListRowSelectionHandler implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            if (!e.getValueIsAdjusting())
            {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                rowIdx = lsm.getMinSelectionIndex();
                System.out.println(rowIdx + " 번째 행 선택");
            }
        }
    }

    class ListColSelectionHandler implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            colIdx = lsm.getMinSelectionIndex(); // 클릭한 열 인덱스를 얻습니다.
            if (!e.getValueIsAdjusting())
            {
                System.out.println(rowIdx + " 번째 행, " + colIdx + "열 선택");
            }
        }

    }

    private void showMessage(String msg)
    {
        JOptionPane.showMessageDialog(this, msg, "메시지 박스", JOptionPane.INFORMATION_MESSAGE);
    }
}
