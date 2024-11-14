-- Insert Books
INSERT INTO books (id, title, author, isbn) VALUES
(RANDOM_UUID(), 'Spring Boot in Action', 'Craig Walls', '9781617292545'),
(RANDOM_UUID(), 'Spring Security in Action', 'Laurentiu Spilca', '9781617297731'),
(RANDOM_UUID(), 'Reactive Spring', 'Josh Long', '9781732910225'),
(RANDOM_UUID(), 'Native Image Definitive Guide', 'Oleg Šelajev', '9781492078531');


-- Insert Speakers
INSERT INTO speakers (id, name, bio, email) VALUES
(RANDOM_UUID(), 'Stéphane Nicoll', 'Spring Framework committer', 'snicoll@pivotal.io'),
(RANDOM_UUID(), 'Rob Winch', 'Spring Security lead', 'rwinch@pivotal.io'),
(RANDOM_UUID(), 'Josh Long', 'Spring Developer Advocate', 'jlong@pivotal.io'),
(RANDOM_UUID(), 'Sébastien Deleuze', 'Spring Framework committer', 'sdeleuze@vmware.com');


-- Insert Conferences
INSERT INTO conferences (id, date, youtube, title, conference, content, duration, room, book_id, speaker_id) VALUES
(RANDOM_UUID(), '2023-05-17', 'https://www.youtube.com/watch?v=TtQTF7M9xPo', 'Spring Boot 3.0 & Spring Framework 6.0: What''s New', 'Spring I/O 2023', 'Overview of new features in Spring Boot 3.0 and Spring Framework 6.0.', 50, 'Auditorium 1', (SELECT id FROM books WHERE title='Spring Boot in Action'), (SELECT id FROM speakers WHERE name='Stéphane Nicoll')),
(RANDOM_UUID(), '2023-05-18', 'https://www.youtube.com/watch?v=wYYKNhCXVVE', 'Securing Spring Boot 3 Applications', 'Spring I/O 2023', 'Exploration of security features in Spring Boot.', 45, 'Room B', (SELECT id FROM books WHERE title='Spring Security in Action'), (SELECT id FROM speakers WHERE name='Rob Winch')),
(RANDOM_UUID(), '2022-05-26', 'https://www.youtube.com/watch?v=TKK4Oi1Xc-Y', 'Reactive Spring', 'Spring I/O 2022', 'Deep dive into reactive programming with Spring.', 55, 'Main Hall', (SELECT id FROM books WHERE title='Reactive Spring'), (SELECT id FROM speakers WHERE name='Josh Long')),
(RANDOM_UUID(), '2024-05-23', 'https://www.youtube.com/placeholder_2024', 'Spring Native and GraalVM: The Future of Spring Applications', 'Spring I/O 2024', 'Exploring advancements in Spring Native.', 60, 'Innovation Theater', (SELECT id FROM books WHERE title='Native Image Definitive Guide'), (SELECT id FROM speakers WHERE name='Sébastien Deleuze'));



