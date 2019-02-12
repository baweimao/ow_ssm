<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script>
$(function () {
	
	//日期选择器
    $.fn.datetimepicker.dates['zh-CN'] = {
			days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
			daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
			daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
			months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一", "十二"],
			monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一", "十二"],
			today: "今天",
			suffix: [],
			meridiem: ["上午", "下午"]
	};
    $("#datetime").datetimepicker({
    	minView: "month",
    	language: 'zh-CN',
    	format: 'yyyy-mm-dd',
    	todayBtn:  1,
    	autoclose: 1,
    });
	
  	//初始化点击下标，并写入本地
    var iCount=0;
    localStorage.setItem("iCount", iCount);
  	//获取此刻时间
    var d = $("#datetime").data("datetimepicker").getDate();
    clickLi(d);
    
  	//获取选择后的时间
    $("#datetime").datetimepicker().on('changeDate', function(){
    	d = $("#datetime").data("datetimepicker").getDate();
    	//下标归零
    	var iCount=0;
        localStorage.setItem("iCount", iCount);
		clickLi(d);
    });

    $("li.left").click(function() {
    	d.setDate(d.getDate()-8);
    	clickLi(d);
    });
    $("li.right").click(function() {
    	d.setDate(d.getDate()-6);
    	clickLi(d);
    });
});

/**
 * 显示代码块，并执行点击效果
 */
function clickLi(d) {
	//获取上次点击的下标
	var iCount = localStorage.getItem("iCount");
	//写入时间条代码块，对应li下标变色
	var _html = insertSpan(d,iCount);
    $("span.date").html(_html);
    
    //获取对应li的时间
	var dt = $("li.gameTimeClick").children("input.time").val();
	//根据时间获取并写入对应对战表代码块
	ajaxGT(dt);
	//加载时间条的点击函数
    $("li.active").click(function() {
    	$(this).addClass("gameTimeClick").siblings().removeClass("gameTimeClick");
		//获取点击后的时间
    	var d = $(this).children("input.time").val();
		//获取点击后新下标
		iCount = $(this).children("input.i").val();
		//写入这次点击的下标
	    localStorage.setItem("iCount", iCount);
		//根据时间获取并写入对应对战表代码块
		ajaxGT(d);
    });
}

/**
 * 异步加载对战表并写入代码块
 */
function ajaxGT(date) {
	var page = "gameTable";
	$.post(
		page,
		{"date":date},
		function(result) {
			var _html = insertDiv(result);
			$("div.gameContent").html(_html);
		}
	)
}

/**
 * 循环对战表代码块
 */
function insertDiv(gs) {
	var _list = "";
	if (gs.length==0){
		_list += '<div class="gameNull background">今天暂无赛事(*￣︶￣)~ 不如去打盘游戏吧~ </div>';
	}
	for (var j = 0; j < gs.length; j++) {
		var g = gs[j];
// 		console.log(j);
		var ls = g.ls;
		var gts = g.gts;
		_list += '<div class="gameTop backgroundC">';
		_list += '<span class="gameName"><a href="'+g.url+'" target="_blank">'+g.name+'</a></span>';
		_list += '<span class="gameLive">直播平台：</span>';
		_list += '<span>';
		for (var i = 0; i < ls.length; i++) {
			var l = ls[i];
			_list += '<a href="'+l.url+'" target="_blank">';
			_list += '<img src="/img/webLogo/'+l.wid+'.jpg">';
			_list += '</a>';
		}
		_list += '</span>';
		_list += '</div>';
		_list += '<table class="gameTable background">';
		_list += '<tbody>';
		for (var i = 0; i < gts.length; i++) {
			var gb = gts[i];
			var m = gb.gameDate;
			var d = new Date(m);
			var year = d.getFullYear();
			var month = d.getMonth();
			month=month>9?month:"0"+month;
			var day = d.getDate();
			day=day>9?day:"0"+day;
			var h = d.getHours();
			h=h>9?h:"0"+h;
			var m = d.getMinutes();
			m=m>9?m:"0"+m; 
			_list += '<tr>';
			_list += '<td class="time">';
			_list += '<span>'+year+' '+month+'-'+day+'</span>';
			_list += '<span>'+h+':'+m+'</span>';
			_list += '</td>';
			_list += '<td class="tanksa">';
			_list += '<span>'+gb.ranks_a.name+'</span>';
			_list += '<img src="/img/ranksLogo/'+gb.ranks_a.id+'.jpg">';
			_list += '</td>';
			_list += '<td class="vs">vs</td>';
			_list += '<td class="tanksb">';
			_list += '<img src="/img/ranksLogo/'+gb.ranks_b.id+'.jpg">';
			_list += '<span>'+gb.ranks_b.name+'</span>';
			_list += '</td>';
			_list += '</tr>';
		}
		_list += '</tbody>';
		_list += '</table>';
	}
	return _list;
}
 
/**
 * 循环时间条代码块
 */
function insertSpan(d,iCount) {
	var _list = "";
	for (var i = 0; i < 7; i++) {
		var month = d.getMonth()+1;
		var day = d.getDate();
		var week = d.getDay();
// 		console.log("C"+iCount);
		if(i==iCount)
			_list += '<li class="active gameTimeClick">';
		else
			_list += '<li class="active">';
		switch (week) {
		case 1:
			_list += '<span class="week">星期一</span>';
			break;
		case 2:
			_list += '<span class="week">星期二</span>';
			break;
		case 3:
			_list += '<span class="week">星期三</span>';
			break;
		case 4:
			_list += '<span class="week">星期四</span>';
			break;
		case 5:
			_list += '<span class="week">星期五</span>';
			break;
		case 6:
			_list += '<span class="week">星期六</span>';
			break;
		case 0:
			_list += '<span class="week">星期日</span>';
			break;
		}
		_list += '<span class="date">'+month+'月'+day+'日</span>';
		_list += '<input class="time" type="hidden" value="'+d+'">';
		_list += '<input class="i" type="hidden" value="'+i+'">';
		_list += '</li>';
		d.setDate(d.getDate()+1);
	}
	return _list;
}
</script>

<div class="nav">
	<p>赛事直播</p>
	<button class="btn btn-default selectDate"  id='datetime'>
		<span class="glyphicon glyphicon-calendar"></span>
	</button>
</div>

<div class="game">
	<div class="gameTime">
		<ul class="backgroundB">
    		<li class="end left">
		        <span>&laquo;</span>
		    </li>
		    <span class="date"></span>
		    <li class="end right">
		        <span>&raquo;</span>
		    </li>
		  </ul>	
	</div>
	<div class="gameContent"></div>
</div>