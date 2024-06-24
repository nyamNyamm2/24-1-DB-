package search;

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

public class ViewSearchDialog extends JDialog
{
    JPanel panelSearch, panelBtn;
    JButton btnSearch;

    JTable scTable;
    String[] columnNames = { "레시피 이름", "아이디", "검색 날짜"};
    RentTableModel rentTableModel;
    Object[][] scItems = new String[0][3];
    int rowIdx = 0, colIdx = 0;

    public ViewSearchDialog(String str)
    {
        setTitle(str);
        init();
    }

    private void init()
    {
        scTable = new JTable();

        ListSelectionModel rowSel = scTable.getSelectionModel();
        rowSel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSel.addListSelectionListener(new ListRowSelectionHandler());

        ListSelectionModel colSel = scTable.getColumnModel().getSelectionModel();
        colSel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        colSel.addListSelectionListener(new ListColSelectionHandler());

        panelSearch = new JPanel();
        panelBtn = new JPanel();

        btnSearch = new JButton("조회하기");
        btnSearch.addActionListener(new SearchBtnHandler());

        panelSearch.add(btnSearch);

        add(panelSearch, BorderLayout.NORTH);

        rentTableModel = new RentTableModel(scItems, columnNames);
        scTable.setModel(rentTableModel);
        add(new JScrollPane(scTable), BorderLayout.CENTER);

        setLocation(300, 100);
        setSize(600, 600);
        setModal(true);
        setVisible(true);
    }


    class SearchBtnHandler implements ActionListener
    {
        String title = null;
        int id = 0;
        String date = null;
        List<SearchVO> scList = null;

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == btnSearch)
            {
                SView scview = new SView();

                try
                {
                    scList = scview.view();
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
                loadTableData(scList);
                return;
            }
            List<SearchVO> scList = new ArrayList<SearchVO>();
            SearchVO scVO = new SearchVO();
            SView scview = new SView();
            try
            {
                scList = scview.view();
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
            loadTableData(scList);
        }
    }

    private void loadTableData(List<SearchVO> scList)
    {
        if (scList != null && scList.size() != 0)
        {
            scItems = new String[scList.size()][3];
            for (int i = 0; i < scList.size(); i++)
            {
                SearchVO scVO = scList.get(i);
                scItems[i][0] = scVO.getSearchTitle();
                scItems[i][1] = Integer.toString(scVO.getSearchId());
                scItems[i][2] = scVO.getSearchDate();
            }

            rentTableModel = new RentTableModel(scItems, columnNames);
            scTable.setModel(rentTableModel);
        }
        else
        {
            message("조회한 정보가 없습니다.");
            scItems = new Object[10][10];
            rentTableModel = new RentTableModel(scItems, columnNames);
            scTable.setModel(rentTableModel);
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
