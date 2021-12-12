package com.example.queryappbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.queryappbackend.domain.AnswerRepository;
import com.example.queryappbackend.domain.Question;
import com.example.queryappbackend.domain.QuestionGroup;
import com.example.queryappbackend.domain.QuestionGroupRepository;
import com.example.queryappbackend.domain.QuestionRepository;

@Controller
public class MaintainerToolsController {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private QuestionGroupRepository groupRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	/*
	 * BACKEND ACCESS FOR QUESTIONNAIRE MAINTAINERS:
	 */
	
	// list all groups
	@GetMapping("/admin/questionnaires")
	public String maintainerListGroups(Model model) {
		model.addAttribute("groups", groupRepository.findAll());
		return "groups";
	}
	
	
	// POST to create a new questionnaire
	@PostMapping("/admin/questionnaires/new")
	public String maintainerAddNewGroup() {
		QuestionGroup g = new QuestionGroup();
		g.setTitle("new questionnaire");
		groupRepository.save(g);

		return "redirect:/admin/questionnaires/";
	}
	
	// POST to edit questionnaire name
	@PostMapping("/admin/questionnaires/{id}")
	public String editGroup(@PathVariable("id") Long qgroupId, String questionnaire) {
		QuestionGroup g = groupRepository.findById(qgroupId).get();
		g.setTitle(questionnaire);
		groupRepository.save(g);
		String url = "redirect:/admin/questionnaires/" + qgroupId + "?t";
		return url;
	}
	
	// delete a questionnaire by path id
	@RequestMapping("/admin/questionnaires/{id}/delete")
	public String deleteGroup(@PathVariable("id") Long qgroupid) {
		groupRepository.deleteById(qgroupid);
		return "redirect:/admin/questionnaires";
	}
	
	
	// list all questions in a group
	@GetMapping("/admin/questionnaires/{id}")
	public String maintainerListQuestions(@PathVariable("id") Long qgroupId, Model model) {
		
		//System.out.println("--//--//--//--//--//--//--//--//--//--//--//--//"+questionRepository.findByQuestionGroupQgroupid(qgroupId));
		model.addAttribute("questions", questionRepository.findByQuestionGroupQgroupid(qgroupId));
		model.addAttribute("qgroupid", qgroupId);
		model.addAttribute("questionnaire", groupRepository.findById(qgroupId).get().getTitle());
		return "questions";
	}

	// show one question for editing
	@GetMapping("/admin/questionnaires/{gid}/{qid}")
	public String maintainerShowQuestion(@PathVariable("gid") Long qgroupId, @PathVariable("qid") Long qId, Model model) {
		model.addAttribute("question", questionRepository.findById(qId).get());
		model.addAttribute("qid", qId);
		model.addAttribute("qgroupid", qgroupId);
		return "editQuestion";
	}
	
	// update a questions description and type
	@PostMapping("/admin/questionnaires/{gid}/{qid}")
	public String maintainerUpdateQuestion(@PathVariable("gid") Long qgroupId, @PathVariable("qid") Long qId,
											String description, String type) {
		Question q = questionRepository.findById(qId).get();
		q.setDescription(description);
		q.setType(type);
		questionRepository.save(q);
		String url = "redirect:/admin/questionnaires/" + qgroupId + "/ " + qId + "?u";
		return url;
	}
	
	// Make a new preset question
	@PostMapping("/admin/questionnaires/{id}/new")
	public String maintainerAddNewQuestionPage(@PathVariable("id") Long qgroupId) {
		
		Question q = new Question();
		q.setType("radio");
		q.setDescription("New Question");
		q.setGroup(groupRepository.findById(qgroupId).get());
		String[] presetAns = new String[5];
		for (int i = 0; i < presetAns.length; i++) {
			presetAns[i] = ""+(i+1);
		}
		q.setAnswers(presetAns);
		questionRepository.save(q);
		String url = "redirect:/admin/questionnaires/"+qgroupId+"?a";
		return url;
	}
	
	// delete a question
	@RequestMapping("/admin/questionnaires/{gid}/{qid}/delete")
	public String maintainerDeleteQuestion(@PathVariable("gid") Long qgroupId, @PathVariable("qid") Long qId, Question q) {
		questionRepository.deleteById(qId); 
		String url = "redirect:/admin/questionnaires/"+qgroupId+"?d";
		return url;
	}
	
	// update an answer
	@PostMapping("/admin/questionnaires/{gid}/{qid}/answers/edit/{index}")
	public String maintainerUpdateQuestionAnswers(@PathVariable("gid") Long qgroupId, @PathVariable("qid") Long qId,
												@PathVariable("index") int index, String answer) {
		Question q = questionRepository.findById(qId).get();
		q.getAnswers()[index] = answer;
		questionRepository.save(q);
		String url = "redirect:/admin/questionnaires/" + qgroupId + "/ " + qId  + "?e";
		return url;
	}
	
	// add answer
	@PostMapping("/admin/questionnaires/{gid}/{qid}/answers/add")
	public String maintainerAddNewAnswer(@PathVariable("gid") Long qgroupId, @PathVariable("qid") Long qId) {
		Question q = questionRepository.findById(qId).get();
		
		//make a new answer array with an extra answer with preset number
		String[] newAns = new String[q.getAnswers().length+1];
		for (int i = 0; i < q.getAnswers().length; i++) {
			newAns[i] = q.getAnswers()[i];
		}
		newAns[newAns.length-1] = ""+newAns.length;
		q.setAnswers(newAns);
		questionRepository.save(q);
		
		String url = "redirect:/admin/questionnaires/" + qgroupId + "/ " + qId + "?a";
		return url;
	}
	
	// delete answer
	@RequestMapping("/admin/questionnaires/{gid}/{qid}/answers/delete/{index}")
	public String maintainerDeleteQuestionAnswer(@PathVariable("gid") Long qgroupId, @PathVariable("qid") Long qId,
												@PathVariable("index") int index) {
		Question q = questionRepository.findById(qId).get();
		
		//make a new answer array without the deleted answer
		String[] newAns = new String[q.getAnswers().length-1];
		int newArrayPointer = 0;
		for (int i = 0; i < q.getAnswers().length; i++) {
			if (index != i) {
				newAns[newArrayPointer] = q.getAnswers()[i];
				newArrayPointer ++;
			}
		}
		q.setAnswers(newAns);
		questionRepository.save(q);
		
		String url = "redirect:/admin/questionnaires/" + qgroupId + "/ " + qId + "?d";
		return url;
	}
}
