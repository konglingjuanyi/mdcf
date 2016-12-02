package com.mindai.cf.scheduler.center.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindai.cf.scheduler.center.enums.RabbitQueues;
import com.mindai.cf.scheduler.center.rabbit.sender.Sender;

import lombok.extern.slf4j.Slf4j;

/**
 * 快捷支付状态查询更新
 * create by lilei 2016-11-18
 * ******/
@Component
@Slf4j
public class QuickPaymentQueryJob  extends QuartzJobBean{
	
	@Autowired
	private Sender sender;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		log.info("QuickPaymentQueryJob定时调度开始-----------------------------------start");
		JobKey jobkey = context.getJobDetail().getKey();
		sender.sendMsg(RabbitQueues.SCHEDULER_PAYGATEWAY_QUICKPAYMENT.getCode(), jobkey.getName());
		log.info("QuickPaymentQueryJob定时调度结束-----------------------------------end");
	
		
	}

	@Override
	public String setCronExpression() {
		return "0 */30 * ? * *";
	}
	
}
