//package org.example.kitsurecs.controller;
//
//import jakarta.inject.Inject;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.example.kitsurecs.model.User;
//import org.example.kitsurecs.model.WatchList;
//import org.example.kitsurecs.model.WatchListItem;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@WebServlet("/watchlist")
//public class WatchListServlet extends HttpServlet {
//
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User currentUser = (User) session.getAttribute("user");
//
//        if (currentUser == null) {
//            // Redirect to login if not logged in
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }
//
//        // Use watchlist DAO instead
//        WatchList watchList = watchListService.getUserWatchList(currentUser.getUser_id());
//        request.setAttribute("watchList", watchList);
//
//        // Forward to the watch list page
//        request.getRequestDispatcher("/WEB-INF/views/watchlist.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User currentUser = (User) session.getAttribute("user");
//
//        if (currentUser == null) {
//            // Redirect to login if not logged in
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }
//
//        String action = request.getParameter("action");
//        String animeId = request.getParameter("animeId");
//        String referer = request.getHeader("Referer"); // For redirecting back to the same page
//
//        if (animeId == null || animeId.isEmpty()) {
//            response.sendRedirect(referer != null ? referer : request.getContextPath() + "/browse");
//            return;
//        }
//
//        try {
//            int animeIdInt;
//            try {
//                animeIdInt = Integer.parseInt(animeId);
//            } catch (NumberFormatException e) {
//                // Handle the case where anime ID can't be parsed to int
//                request.getSession().setAttribute("error", "Invalid anime ID format");
//                response.sendRedirect(referer != null ? referer : request.getContextPath() + "/browse");
//                return;
//            }
//
//            //Use watchlistDAO instead
//            WatchList userWatchList = watchListService.getUserWatchList(currentUser.getUser_id());
//
//            if (userWatchList == null) {
//                // Create a new watch list if the user doesn't have one
//                userWatchList = new WatchList(
//                        UUID.randomUUID().toString(),
//                        UUID.randomUUID().toString(),
//                        currentUser.getUser_id(),
//                        new ArrayList<>()
//                );
//                //Create WatchListDAO
//                watchListService.createWatchList(userWatchList);
//            }
//
//            if ("add".equals(action)) {
//                // Add to watch list
//                boolean watched = false;
//                boolean favorite = false;
//
//                String watchListItemId = UUID.randomUUID().toString();
//                WatchListItem item = userWatchList.AddWatchListItem(watchListItemId, animeIdInt, watched, favorite);
//                //Update Watchlist DAO
//                watchListService.saveWatchList(userWatchList);
//
//                request.getSession().setAttribute("message", "Anime added to your watch list!");
//
//            } else if ("remove".equals(action)) {
//                // Remove from watch list
//                List<WatchListItem> items = userWatchList.getWatchListItems();
//                items.removeIf(item -> item.getAnimeId() == animeIdInt);
//                //Update WatchlistDAO
//                watchListService.saveWatchList(userWatchList);
//
//                request.getSession().setAttribute("message", "Anime removed from your watch list!");
//
//            } else if ("update".equals(action)) {
//                // Update watched and favorite status
//                boolean watched = "true".equals(request.getParameter("watched"));
//                boolean favorite = "true".equals(request.getParameter("favorite"));
//
//                // Find and update the item
//                for (WatchListItem item : userWatchList.getWatchListItems()) {
//                    if (item.getAnimeId() == animeIdInt) {
//                        // Update the item - need to create a new one due to immutability
//                        WatchListItem updatedItem = new WatchListItem(
//                                item.getWatchListItemId(),
//                                item.getAnimeId(),
//                                watched,
//                                favorite
//                        );
//
//                        // Replace the item in the list
//                        List<WatchListItem> items = userWatchList.getWatchListItems();
//                        int index = items.indexOf(item);
//                        if (index >= 0) {
//                            items.set(index, updatedItem);
//                        }
//                        break;
//                    }
//                }
//                //Update WatchList DAO
//                watchListService.saveWatchList(userWatchList);
//                request.getSession().setAttribute("message", "Watch list updated!");
//            }
//        } catch (Exception e) {
//            request.getSession().setAttribute("error", "Failed to update watch list: " + e.getMessage());
//        }
//
//        // Redirect back to the previous page
//        response.sendRedirect(referer != null ? referer : request.getContextPath() + "/browse");
//    }
//}