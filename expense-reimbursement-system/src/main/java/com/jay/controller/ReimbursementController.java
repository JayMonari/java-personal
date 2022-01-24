package com.jay.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.jay.dto.ReimbursementDto;
import com.jay.model.Reimbursement;
import com.jay.model.User;
import com.jay.service.ReimbursementService;
import com.jay.util.JavalinUtil;
import com.jay.util.JwtUtil;

import io.javalin.http.Context;

public class ReimbursementController {
  private static ReimbursementService reimbursementService;

  public ReimbursementController(ReimbursementService reimbursementService) {
    ReimbursementController.reimbursementService = reimbursementService;
  }

  public static void getAllReimbursements(Context context) {
    String username = JwtUtil.extractUsernameFromJwt(context);
    List<ReimbursementDto> reimbursementDtos = reimbursementService.filterAllByUser(username);
    context.json(reimbursementDtos);
  }

  public static void getAllReimbursementsForUser(Context context) {
    String username = JwtUtil.extractUsernameFromJwt(context);
    System.out.println(username);
    List<ReimbursementDto> reimbursements = reimbursementService.findAllReimbursementsForUser(username);
    context.json(reimbursements);
  }

  public static void addReimbursement(Context context) {
    String path = System.getProperty("user.dir") + "/frontend/html/addReimbursement.html";
    try {
      String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
      context.html(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void postReimbursement(Context context) {
    Reimbursement reimbursement = context.bodyAsClass(Reimbursement.class);
    String username = JwtUtil.extractUsernameFromJwt(context);
    reimbursement.setAuthor(User.builder().username(username).build());
    System.out.println(reimbursement);
    reimbursementService.save(reimbursement);
    context.redirect(JavalinUtil.EMPLOYEE, 302);
  }

  public static void updateReimbursement(Context context) {
    Reimbursement incompleteReimbursement = context.bodyAsClass(Reimbursement.class);
    String username = JwtUtil.extractUsernameFromJwt(context);
    incompleteReimbursement.setResolver(User.builder().username(username).build());

    String resolver = reimbursementService.update(incompleteReimbursement);
    context.result(resolver);
  }
}
