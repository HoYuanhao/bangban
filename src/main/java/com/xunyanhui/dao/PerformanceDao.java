package com.xunyanhui.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunyanhui.model.EntryRecord;
import com.xunyanhui.model.Performance;

public interface PerformanceDao {

	public List<Performance> getPerformanceList(
			@Param(value = "date") Date date,
			@Param(value = "order") int order,
			@Param(value = "type") String type,
			@Param(value = "beginIndex") int beginIndex,
			@Param(value = "pageCount") int pageCount,
			@Param(value="ccity")String ccity
			);

	public EntryRecord getEntryRecordByUidAndPerforId(
			@Param(value = "uid") String uid,
			@Param(value = "performanceId") String performanceId);

	public void addPerformance(Performance p);

	
	public int getSignUpById(String id);
	
	public void updateSignUpNum(String id);
	
	public int hasPerforByIdAndUid(@Param("id") String id,
			@Param("uid") String uid);

	public Performance getPerformanceDetails(String id);

	public int isSelf(@Param("performanceId") String performanceId,
			@Param("employerId") String employerId);

	public List<Performance> searchByKey(@Param("date") Date date,
			@Param("key") String key, @Param("beginIndex") int beginIndex,
			@Param("pageCount") int pageCount,
			@Param("ccity") String ccity
			
			);
}
