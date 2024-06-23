package recipe;

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

public class SearchRecipeDialog extends JDialog
{
    JPanel panelSearch, panelBtn;
    JButton btnSearch;
    JButton btnModify;
    JButton btnDelete;
    JButton btnContent;


    JTable recipeTable;
    String[] columnNames = { "레시피이름", "아이디", "간략한 설명", "난이도"};
    RentTableModel rentTableModel;
    Object[][] reciItems = new String[0][4];
    int rowIdx = 0, colIdx = 0;

    public SearchRecipeDialog(String str)
    {
        setTitle(str);
        init();
    }

    private void init()
    {
        recipeTable = new JTable();
        ListSelectionModel rowSel = recipeTable.getSelectionModel();
        rowSel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSel.addListSelectionListener(new ListRowSelectionHandler());

        ListSelectionModel colSel = recipeTable.getColumnModel().getSelectionModel();
        colSel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colSel.addListSelectionListener(new ListColSelectionHandler());

        panelSearch = new JPanel();
        panelBtn = new JPanel();

        btnSearch = new JButton("조회하기");
        btnSearch.addActionListener(new RecipeBtnHandler());

        panelSearch.add(btnSearch);

        btnContent = new JButton("자세히보기");
        btnModify = new JButton("수정하기");
        btnDelete = new JButton("삭제하기");

        btnContent.addActionListener(new RecipeBtnHandler());
        btnModify.addActionListener(new RecipeBtnHandler());
        btnDelete.addActionListener(new RecipeBtnHandler());

        panelBtn.add(btnContent);
        panelBtn.add(btnModify);
        panelBtn.add(btnDelete);

        add(panelSearch, BorderLayout.NORTH);
        add(panelBtn, BorderLayout.SOUTH);

        rentTableModel = new RentTableModel(reciItems, columnNames);
        recipeTable.setModel(rentTableModel);
        add(new JScrollPane(recipeTable), BorderLayout.CENTER);

        setLocation(300, 100);
        setSize(600, 600);
        setModal(true);
        setVisible(true);
    }

    class RecipeBtnHandler implements ActionListener
    {
        String title = null;
        int id = 0;
        String content = null;
        String diff = null;
        List<RecipeVO> reciList = null;

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == btnSearch)
            {
                RView reciview = new RView();
                try
                {
                    reciList = reciview.view();
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
                loadTableData(reciList);
                return;
            }
            else if (e.getSource() == btnDelete)
            {
                title = (String) reciItems[rowIdx][0];
                id = Integer.parseInt((String) reciItems[rowIdx][1]);
                content = (String) reciItems[rowIdx][2];
                diff = (String) reciItems[rowIdx][3];
                RecipeVO reciVO = new RecipeVO(title, id, content, diff);
                try
                {
                    new RDelete(reciVO);
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
                showMessage("레시피 정보를 삭제했습니다.");
            }
            else if (e.getSource() == btnModify)
            {
                title = (String) reciItems[rowIdx][0];
                id = Integer.parseInt((String) reciItems[rowIdx][1]);
                content = (String) reciItems[rowIdx][2];
                diff = (String) reciItems[rowIdx][3];
                RecipeVO reciVO = new RecipeVO(title, id, content, diff);

                new ModifyRecipeDialog("레시피 수정 창", title);

                RView reciview = new RView();
                try
                {
                    reciList = reciview.view();
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
                loadTableData(reciList);
            }
            else if (e.getSource() == btnContent)
            {
                title = (String) reciItems[rowIdx][0];
                content = (String) reciItems[rowIdx][2];

                new CheckUser(title, content);
            }

            List<RecipeVO> reciList = new ArrayList<RecipeVO>();
            RecipeVO reciVO = new RecipeVO();
            RView reciview = new RView();
            try
            {
                reciList = reciview.view();
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
            loadTableData(reciList);

        }
    }

    private void loadTableData(List<RecipeVO> reciList)
    {
        if (reciList != null && reciList.size() != 0)
        {
            reciItems = new String[reciList.size()][4];
            for (int i = 0; i < reciList.size(); i++)
            {
                RecipeVO reciVO = reciList.get(i);
                reciItems[i][0] = reciVO.getRecipeTitle();
                reciItems[i][1] = Integer.toString(reciVO.getRecipeId());
                reciItems[i][2] = reciVO.getRecipeContent();
                reciItems[i][3] = reciVO.getRecipeDiff();
            }

            rentTableModel = new RentTableModel(reciItems, columnNames);
            recipeTable.setModel(rentTableModel);
        }
        else
        {
            message("조회한 정보가 없습니다.");
            reciItems = new Object[10][10];
            rentTableModel = new RentTableModel(reciItems, columnNames);
            recipeTable.setModel(rentTableModel);
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
