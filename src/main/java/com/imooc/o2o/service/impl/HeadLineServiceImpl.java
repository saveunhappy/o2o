package com.imooc.o2o.service.impl;

import com.imooc.o2o.dto.HeadLineExecution;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.entity.HeadLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.HeadLineDao;
import com.imooc.o2o.service.HeadLineService;

import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
	@Autowired
	private HeadLineDao headLineDao;

	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
		return headLineDao.queryHeadLine(headLineCondition);
	}

	@Override
	public HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder thumbnail) {
		return null;
	}

	@Override
	public HeadLineExecution modifyHeadLine(HeadLine headLine, ImageHolder thumbnail) {
		return null;
	}

	@Override
	public HeadLineExecution removeHeadLine(long headLineId) {
		return null;
	}

	@Override
	public HeadLineExecution removeHeadLineList(List<Long> headLineIdList) {
		return null;
	}
}
