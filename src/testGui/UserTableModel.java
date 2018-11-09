package testGui;

import java.util.*;

import javax.swing.table.AbstractTableModel;

class UserTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	// 保存一个User的列表
	private List<User> users = new ArrayList<User>();
    public final static String[] columnNames = {"学号","姓名","性别","工作单位","联系方式","E-mail"	};

	// 设置User列表, 同时通知JTabel数据对象更改, 重绘界面
	public void setUsers(List<User> users) {
		this.users = users;
		this.fireTableDataChanged();// 同时通知JTabel数据对象更改, 重绘界面
	}
	public int getColumnCount() {
		return columnNames.length;
	}
	public int getRowCount() {
		return users.size();
	}
	public String getColumnName(int column){
        return columnNames[column];
	}
	
	public boolean isCellEditable(int row, int column){
        return false;
      }
	
	// 从list中拿出rowIndex行columnIndex列显示的值
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = users.get(rowIndex);
		if (columnIndex == 0) {
			return user.getUserId();
		} else if (columnIndex == 1){
			return user.getUserName();
		}else if (columnIndex == 2){
			return user.getSex();
		}else if (columnIndex == 3){
			return user.getWorkSddress();
		}else if (columnIndex == 4){
			return user.getPhone();
		}else if (columnIndex == 5){
			return user.getMail();
		}else {
			return null;
		}
	}
}