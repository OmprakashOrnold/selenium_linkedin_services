package com.aldrich.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.aldrich.dao.JobsDAO;
import com.aldrich.entity.Jobs;
import com.aldrich.util.PASEConstants;
@Repository
@SuppressWarnings({ "nls", "boxing" })
public class JobsDAOImpl implements JobsDAO {

	@Autowired
	MongoTemplate mongoTemplate;
	private static final String INDEED_JOBS_COLLECTION = "job_info";

	
	public JobsDAOImpl() {
	}

	@Override
	public void save(Jobs indeedJobs) {
		try {
			this.mongoTemplate.insert(indeedJobs, INDEED_JOBS_COLLECTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Jobs checkForExistance(Jobs indeedJobs) {
		Criteria criteria = new Criteria().andOperator(Criteria.where("fk_company_id").is(indeedJobs.getCompanyId()),
				Criteria.where("posted_datetime").is(indeedJobs.getPosted_datetime()),
				Criteria.where("title").is(indeedJobs.getJobtitle().trim()),
				Criteria.where("location").is(indeedJobs.getLocation().trim()));
		Query query = new Query(criteria);
		return this.mongoTemplate.findOne(query, Jobs.class, INDEED_JOBS_COLLECTION);
	}

	@Override
	public List<Jobs> getIndeedJobTitles() {
		Criteria criteria = new Criteria().andOperator(Criteria.where("fk_source_id").is(1),
				Criteria.where("title").is("Entry Level Outside Sales"), Criteria.where("fk_company_id").is(3246));
		Query query = new Query(criteria);
		return this.mongoTemplate.find(query, Jobs.class, INDEED_JOBS_COLLECTION);
	}

	@Override
	public List<Jobs> getCompanyJobs(Long companyId) {
		Query query = null;
		Criteria criteria = null;
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, -90);
		dt = c.getTime();
		try {
			criteria = new Criteria();
			criteria.andOperator(Criteria.where("fk_company_id").is(companyId),
					Criteria.where("posted_datetime").gte(dt),
					criteria.orOperator(Criteria.where("confidence_score").gt(0.8),
							Criteria.where("fk_source_id").is(PASEConstants.INDEED_SOURCE_ID)));
			query = new Query(criteria);
		} catch (Exception excp) {
			excp.printStackTrace();
		}
		return this.mongoTemplate.find(query, Jobs.class, INDEED_JOBS_COLLECTION);
	}

	@Override
	public List<Jobs> getCompanyJobsWithoutTimeFrame(Long companyId) {
		Query query = null;
		Criteria criteria = null;
		try {
			criteria = new Criteria();
			criteria.andOperator(Criteria.where("fk_company_id").is(companyId),
					Criteria.where("fk_source_id").is(PASEConstants.INDEED_SOURCE_ID));
			query = new Query(criteria);
		} catch (Exception excp) {
			excp.printStackTrace();
		}
		return this.mongoTemplate.find(query, Jobs.class, INDEED_JOBS_COLLECTION);
	}

	@Override
	public List<Jobs> getCompanyJobsForAPI(Long companyId) {
		Query query = null;
		Criteria criteria = null;
		try {
			criteria = new Criteria();
			criteria.andOperator(Criteria.where("fk_company_id").is(companyId),
					criteria.orOperator(Criteria.where("confidence_score").gt(0.8),
							Criteria.where("fk_source_id").is(PASEConstants.INDEED_SOURCE_ID)));
			query = new Query(criteria);
			query.with(new Sort(Sort.Direction.DESC, "posted_datetime"));
			query.limit(10);
		} catch (Exception excp) {
			excp.printStackTrace();
		}
		return this.mongoTemplate.find(query, Jobs.class, INDEED_JOBS_COLLECTION);
	}

	@Override
	public void updateJobsValidation(long companyId, String jobUrl) {
		try {
			Criteria criteria = new Criteria().andOperator(Criteria.where("fk_company_id").is(companyId),
					Criteria.where("url").is(jobUrl));
			Query query = new Query(criteria);
			Update update = new Update();
			update.set("type", "Yes");
			// this.mongoOps.updateFirst(query, update, INDEED_JOBS_COLLECTION);
			this.mongoTemplate.updateMulti(query, update, INDEED_JOBS_COLLECTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}