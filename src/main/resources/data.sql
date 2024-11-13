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