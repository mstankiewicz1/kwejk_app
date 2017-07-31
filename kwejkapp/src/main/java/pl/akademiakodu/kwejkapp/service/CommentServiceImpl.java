package pl.akademiakodu.kwejkapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akademiakodu.kwejkapp.dao.CommentDao;
import pl.akademiakodu.kwejkapp.model.Comment;

import java.time.LocalDateTime;

/**
 * Created by itml on 25.06.2017.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }
}
