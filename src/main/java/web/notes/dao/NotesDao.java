package web.notes.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import web.notes.vo.Notes;
import web.sched.vo.Dest;
import web.sched.vo.Sched;

public interface  NotesDao {

	int update(Notes notes);

	Notes getRecordByDstNo(int id , int memNo);
	
	int create(Notes notes);
	
	Dest getImageByDest(int dstNo);
}
