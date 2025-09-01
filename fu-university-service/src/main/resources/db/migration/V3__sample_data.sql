INSERT INTO universities (name, description, type, established_year, website_url, rating, total_students, country, city,
                          is_active)
VALUES ('VIZJA University',
        'VIZJA University is a modern private university in Warsaw, Poland,' ||
        ' offering innovative programs in business, technology, arts, and health sciences.' ||
        ' Known for its international approach and English-taught programs.',
        'PRIVATE',
        2001,
        'https://vizja.pl',
        4.5,
        16000,
        'Poland',
        'Warsaw',
        true);

-- Insert Programs (using a subquery to get university_id)
INSERT INTO programs (name, description, level, duration, language, tuition, rating, students_count, next_deadline,
                      university_id)
VALUES
-- Program 1: Psychology
('Psychology',
 'Our Psychology Master''s program at VIZJA University is renowned and recommended by the Polish Academy of Sciences. The program combines theoretical foundations with practical experience in clinical settings, preparing students for careers in therapy, research, and psychological consultation.',
 'MASTER',
 2.0,
 'English',
 14000.00,
 4.8,
 1200,
 '2025-08-01 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 2: Computer Science - Artificial Intelligence
('Computer Science - Artificial Intelligence',
 'This cutting-edge Bachelor''s program focuses on Artificial Intelligence technologies and applications. Students learn to develop AI systems, work with machine learning algorithms, and create intelligent solutions for modern technological challenges in various industries.',
 'BACHELOR',
 4.0,
 'English',
 16000.00,
 4.6,
 450,
 '2025-07-30 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 3: Finance and Accounting
('Finance and Accounting',
 'Our Finance and Accounting Master''s program prepares students for senior positions in financial institutions, corporations, and consulting firms. The curriculum covers advanced financial analysis, international markets, and modern fintech solutions.',
 'MASTER',
 2.0,
 'English',
 13500.00,
 4.5,
 680,
 '2025-08-15 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 4: Fashion Design & Sustainable Fashion Management
('Fashion Design & Sustainable Fashion Management',
 'This innovative Bachelor''s program combines creative fashion design with sustainable business practices.' ||
 ' Students learn to create fashion collections while understanding environmental impact, ethical production,' ||
 ' and sustainable fashion business models.',
 'BACHELOR',
 3.0,
 'English',
 17000.00,
 4.4,
 320,
 '2025-07-20 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 5: Cybersecurity
('Cybersecurity',
 'Our Cybersecurity Bachelor''s program addresses the growing demand for cybersecurity professionals. ' ||
 'Students learn to protect digital infrastructure, conduct security assessments, ' ||
 'and respond to cyber threats in various organizational contexts.',
 'BACHELOR',
 4.0,
 'English',
 15500.00,
 4.7,
 380,
 '2025-08-10 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 6: International Relations
('International Relations',
 'This Master''s program in International Relations prepares students for careers in diplomacy,' ||
 ' international organizations, NGOs, and multinational corporations. The curriculum focuses on contemporary global challenges and' ||
 ' diplomatic solutions.',
 'MASTER',
 2.0,
 'English',
 12500.00,
 4.3,
 520,
 '2025-08-05 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 7: Sports Business
('Sports Business',
 'Our Sports Business Bachelor''s program combines business management principles with sports industry expertise.' ||
 ' Students learn to manage sports organizations, develop marketing strategies, and understand the economics of modern sports business.',
 'BACHELOR',
 3.0,
 'English',
 14500.00,
 4.2,
 280,
 '2025-07-25 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 8: Architecture
('Architecture',
 'This intensive Master''s program in Architecture focuses on contemporary architectural challenges including sustainable design,' ||
 ' smart buildings, and urban development. Students work on real projects and collaborate with industry professionals.',
 'MASTER',
 1.5,
 'English',
 18000.00,
 4.6,
 200,
 '2025-07-10 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 9: Pharmacy
('Pharmacy',
 'Our comprehensive Pharmacy program is a 5.5-year Master''s degree that meets all national and international standards. ' ||
 'Students receive extensive training in pharmaceutical sciences, clinical practice, and patient care, with hands-on experience in modern laboratories.',
 'MASTER',
 5.5,
 'English',
 22000.00,
 4.9,
 150,
 '2025-06-30 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 10: Marketing Business Management
('Marketing Business Management',
 'This Master''s program combines advanced marketing strategies with business management skills.' ||
 ' Students learn to develop comprehensive marketing campaigns, analyze consumer behavior, and manage marketing teams in the digital age.',
 'MASTER',
 2.0,
 'English',
 13000.00,
 4.4,
 590,
 '2025-08-20 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 11: English Philology
('English Philology',
 'Our English Philology Bachelor''s program offers comprehensive study of English language, literature, and culture. Students develop advanced language skills and cultural competency, preparing for careers in education, translation, publishing, and international communication.',
 'BACHELOR',
 3.0,
 'English',
 11500.00,
 4.1,
 420,
 '2025-08-30 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University')),

-- Program 12: Real Estate and Investments
('Real Estate and Investments',
 'This Bachelor''s program focuses on real estate investment, property management, and urban development.' ||
 ' Students learn to analyze property markets, manage investment portfolios, and understand legal aspects of real estate transactions.',
 'BACHELOR',
 3.0,
 'English',
 14000.00,
 4.3,
 310,
 '2025-08-25 23:59:59+02:00',
 (SELECT id FROM universities WHERE name = 'VIZJA University'));

-- Insert Tags
INSERT INTO tags (name)
VALUES
-- Psychology tags
('Clinical Psychology'),
('Cognitive Psychology'),
('Behavioral Analysis'),
('Research Methods'),
('Therapy Techniques'),
('Psychological Assessment'),
('Mental Health'),
('Human Development'),
-- AI tags
('Machine Learning'),
('Deep Learning'),
('Neural Networks'),
('Data Science'),
('Computer Vision'),
('Natural Language Processing'),
('Robotics'),
('Algorithm Design'),
-- Finance tags
('Corporate Finance'),
('Financial Markets'),
('Audit & Control'),
('Investment Analysis'),
('Risk Management'),
('International Finance'),
('Financial Technology'),
('Business Valuation'),
-- Fashion tags
('Fashion Design'),
('Sustainable Fashion'),
('Textile Innovation'),
('Brand Management'),
('Fashion Marketing'),
('Eco-friendly Materials'),
('Fashion Technology'),
('Creative Direction'),
-- Cybersecurity tags
('Network Security'),
('Ethical Hacking'),
('Digital Forensics'),
('Cyber Threat Analysis'),
('Information Security'),
('Penetration Testing'),
('Security Architecture'),
('Incident Response'),
-- International Relations tags
('Diplomacy'),
('Global Politics'),
('International Law'),
('EU Studies'),
('Conflict Resolution'),
('International Economics'),
('Foreign Policy Analysis'),
('Security Studies'),
-- Sports Business tags
('Sports Management'),
('Sports Marketing'),
('Event Management'),
('Sports Economics'),
('Fan Engagement'),
('Sports Analytics'),
('Sponsorship Management'),
('Digital Sports Media'),
-- Architecture tags
('Architectural Design'),
('Urban Planning'),
('Sustainable Architecture'),
('Building Technology'),
('3D Modeling'),
('Construction Management'),
('Historic Preservation'),
('Green Building Design'),
-- Pharmacy tags
('Pharmaceutical Sciences'),
('Clinical Pharmacy'),
('Drug Development'),
('Pharmacology'),
('Medical Chemistry'),
('Pharmaceutical Technology'),
('Patient Care'),
('Healthcare Systems'),
-- Marketing tags
('Digital Marketing'),
('Consumer Behavior'),
('Marketing Analytics'),
('Social Media Marketing'),
('Market Research'),
('Product Management'),
('Customer Experience'),
-- English Philology tags
('English Literature'),
('Linguistics'),
('Translation Studies'),
('Cultural Studies'),
('Language Teaching'),
('Creative Writing'),
('Literary Analysis'),
('Communication Skills'),
-- Real Estate tags
('Property Management'),
('Real Estate Investment'),
('Property Valuation'),
('Urban Development'),
('Real Estate Law'),
('Portfolio Management'),
('Market Analysis'),
('Construction Finance')
ON CONFLICT (name) DO NOTHING;


-- Psychology program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'Psychology'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN
      ('Clinical Psychology', 'Cognitive Psychology', 'Behavioral Analysis', 'Research Methods', 'Therapy Techniques',
       'Psychological Assessment', 'Mental Health', 'Human Development');

-- AI program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'Computer Science - Artificial Intelligence'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN ('Machine Learning', 'Deep Learning', 'Neural Networks', 'Data Science', 'Computer Vision',
                 'Natural Language Processing', 'Robotics', 'Algorithm Design');

-- Finance program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'Finance and Accounting'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN ('Corporate Finance', 'Financial Markets', 'Audit & Control', 'Investment Analysis', 'Risk Management',
                 'International Finance', 'Financial Technology', 'Business Valuation');

-- Fashion program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'Fashion Design & Sustainable Fashion Management'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN ('Fashion Design', 'Sustainable Fashion', 'Textile Innovation', 'Brand Management', 'Fashion Marketing',
                 'Eco-friendly Materials', 'Fashion Technology', 'Creative Direction');

-- Cybersecurity program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'Cybersecurity'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN
      ('Network Security', 'Ethical Hacking', 'Digital Forensics', 'Cyber Threat Analysis', 'Information Security',
       'Penetration Testing', 'Security Architecture', 'Incident Response');

-- International Relations program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'International Relations'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN ('Diplomacy', 'Global Politics', 'International Law', 'EU Studies', 'Conflict Resolution',
                 'International Economics', 'Foreign Policy Analysis', 'Security Studies');

-- Sports Business program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'Sports Business'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN ('Sports Management', 'Sports Marketing', 'Event Management', 'Sports Economics', 'Fan Engagement',
                 'Sports Analytics', 'Sponsorship Management', 'Digital Sports Media');

-- Architecture program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'Architecture'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN
      ('Architectural Design', 'Urban Planning', 'Sustainable Architecture', 'Building Technology', '3D Modeling',
       'Construction Management', 'Historic Preservation', 'Green Building Design');

-- Pharmacy program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'Pharmacy'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN
      ('Pharmaceutical Sciences', 'Clinical Pharmacy', 'Drug Development', 'Pharmacology', 'Medical Chemistry',
       'Pharmaceutical Technology', 'Patient Care', 'Healthcare Systems');

-- Marketing Business Management program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'Marketing Business Management'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN
      ('Digital Marketing', 'Brand Management', 'Consumer Behavior', 'Marketing Analytics', 'Social Media Marketing',
       'Market Research', 'Product Management', 'Customer Experience');

-- English Philology program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'English Philology'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN ('English Literature', 'Linguistics', 'Translation Studies', 'Cultural Studies', 'Language Teaching',
                 'Creative Writing', 'Literary Analysis', 'Communication Skills');

-- Real Estate program tags
INSERT INTO program_tags (program_id, tag_id)
SELECT p.id, t.id
FROM programs p,
     tags t
WHERE p.name = 'Real Estate and Investments'
  AND p.university_id = (SELECT id FROM universities WHERE name = 'VIZJA University')
  AND t.name IN
      ('Property Management', 'Real Estate Investment', 'Property Valuation', 'Urban Development', 'Real Estate Law',
       'Portfolio Management', 'Market Analysis', 'Construction Finance');


-- Insert Requirements
-- ============================================================
-- Requirements (deduplicated, inserted only once)
-- ============================================================
INSERT INTO requirements (description, type)
VALUES
-- Generic
('High school diploma', 'ACADEMIC'),
('High school diploma with strong mathematics background', 'ACADEMIC'),
('High school diploma with strong IT background', 'ACADEMIC'),
('Excellent grades in Chemistry, Biology, and Mathematics', 'ACADEMIC'),
('Bachelor''s degree in Psychology or related field', 'ACADEMIC'),
('Bachelor''s degree in Finance, Economics, or Business', 'ACADEMIC'),
('Bachelor''s degree in Marketing, Business, or related field', 'ACADEMIC'),
('Bachelor''s degree in Architecture or Engineering', 'ACADEMIC'),
('Bachelor''s degree in Political Science, International Relations, or related field', 'ACADEMIC'),

-- GPA / grade
('Minimum GPA of 3.2 (or equivalent)', 'ACADEMIC'),
('Minimum GPA of 3.0 (or equivalent)', 'ACADEMIC'),
('Minimum GPA of 3.5 (or equivalent)', 'ACADEMIC'),
('Minimum grade average of 65%', 'ACADEMIC'),
('Minimum grade average of 70%', 'ACADEMIC'),
('Minimum grade average of 75%', 'ACADEMIC'),

-- Language
('IELTS 6.0 or TOEFL iBT 80', 'ACADEMIC'),
('IELTS 6.5 or TOEFL iBT 85', 'ACADEMIC'),
('IELTS 6.5 or TOEFL iBT 90', 'ACADEMIC'),
('IELTS 7.0 or TOEFL iBT 95', 'ACADEMIC'),
('IELTS 7.5 or TOEFL iBT 100', 'ACADEMIC'),

-- Statements
('Statement of Purpose (400-700 words)', 'ACADEMIC'),
('Statement of Purpose (500-750 words)', 'ACADEMIC'),
('Statement of Purpose (500-800 words)', 'ACADEMIC'),
('Statement of Purpose (600-800 words)', 'ACADEMIC'),
('Statement of Purpose (600-900 words)', 'ACADEMIC'),
('Statement of Purpose (700-1000 words)', 'ACADEMIC'),
('Statement of Purpose (750-1000 words)', 'ACADEMIC'),
('Statement of Purpose (800-1000 words)', 'ACADEMIC'),
('Statement of Purpose (1000 words)', 'ACADEMIC'),

-- References
('One academic reference', 'ACADEMIC'),
('Two academic references', 'ACADEMIC'),
('Two professional or academic references', 'ACADEMIC'),
('Two academic or professional references', 'ACADEMIC'),

-- Extras
('CV/Resume with relevant experience', 'ACADEMIC'),
('CV with relevant work experience', 'ACADEMIC'),
('CV with marketing or business experience', 'ACADEMIC'),
('Psychology portfolio or research experience preferred', 'ACADEMIC'),
('Portfolio of creative work (fashion, art, or design)', 'ACADEMIC'),
('Portfolio of architectural/design work', 'ACADEMIC'),
('Portfolio of marketing projects preferred', 'ACADEMIC'),
('Creative aptitude test', 'ACADEMIC'),
('Basic drawing or design skills preferred', 'ACADEMIC'),
('Mathematics proficiency test', 'ACADEMIC'),
('Programming experience preferred but not required', 'ACADEMIC'),
('Basic programming knowledge preferred', 'ACADEMIC'),
('Knowledge of additional languages preferred', 'ACADEMIC'),
('Basic knowledge of accounting principles', 'ACADEMIC'),
('Basic business knowledge preferred', 'ACADEMIC'),
('Basic mathematics and economics knowledge', 'ACADEMIC'),
('Demonstrated interest in sports industry', 'ACADEMIC'),
('Interest in property and investment markets', 'ACADEMIC'),
('Interest in information security demonstrated', 'ACADEMIC'),
('Medical fitness certificate', 'ACADEMIC'),
('Interview may be required', 'ACADEMIC'),
('English language proficiency interview', 'ACADEMIC'),
('Literature or language studies background preferred', 'ACADEMIC'),
('Advanced English proficiency (C1 level)', 'ACADEMIC'),
('Proficiency in CAD software required', 'ACADEMIC');

-- Insert Requirements for each program
-- ============================================================
-- Psychology
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'Bachelor''s degree in Psychology or related field',
                                                  'Minimum GPA of 3.2 (or equivalent)',
                                                  'IELTS 7.0 or TOEFL iBT 95',
                                                  'Statement of Purpose (750-1000 words)',
                                                  'Two academic references',
                                                  'CV/Resume with relevant experience',
                                                  'Psychology portfolio or research experience preferred'
    )
WHERE p.name = 'Psychology'
  AND u.name = 'VIZJA University';


-- ============================================================
-- Computer Science - Artificial Intelligence
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'High school diploma with strong mathematics background',
                                                  'Minimum grade average of 75%',
                                                  'IELTS 6.5 or TOEFL iBT 85',
                                                  'Statement of Purpose (500-750 words)',
                                                  'One academic reference',
                                                  'Mathematics proficiency test',
                                                  'Programming experience preferred but not required'
    )
WHERE p.name = 'Computer Science - Artificial Intelligence'
  AND u.name = 'VIZJA University';


-- ============================================================
-- Finance and Accounting
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'Bachelor''s degree in Finance, Economics, or Business',
                                                  'Minimum GPA of 3.0 (or equivalent)',
                                                  'IELTS 6.5 or TOEFL iBT 90',
                                                  'Statement of Purpose (600-900 words)',
                                                  'Two professional or academic references',
                                                  'CV with relevant work experience',
                                                  'Basic knowledge of accounting principles'
    )
WHERE p.name = 'Finance and Accounting'
  AND u.name = 'VIZJA University';


-- ============================================================
-- Fashion Design & Sustainable Fashion Management
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'High school diploma',
                                                  'Portfolio of creative work (fashion, art, or design)',
                                                  'IELTS 6.0 or TOEFL iBT 80',
                                                  'Statement of Purpose (400-700 words)',
                                                  'One academic reference',
                                                  'Creative aptitude test',
                                                  'Basic drawing or design skills preferred'
    )
WHERE p.name = 'Fashion Design & Sustainable Fashion Management'
  AND u.name = 'VIZJA University';


-- ============================================================
-- Cybersecurity
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'High school diploma with strong IT background',
                                                  'Minimum grade average of 70%',
                                                  'IELTS 6.5 or TOEFL iBT 85',
                                                  'Statement of Purpose (500-800 words)',
                                                  'One academic reference',
                                                  'Basic programming knowledge preferred',
                                                  'Interest in information security demonstrated'
    )
WHERE p.name = 'Cybersecurity'
  AND u.name = 'VIZJA University';


-- ============================================================
-- International Relations
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'Bachelor''s degree in Political Science, International Relations, or related field',
                                                  'Minimum GPA of 3.0 (or equivalent)',
                                                  'IELTS 7.0 or TOEFL iBT 95',
                                                  'Statement of Purpose (700-1000 words)',
                                                  'Two academic or professional references',
                                                  'CV with relevant experience',
                                                  'Knowledge of additional languages preferred'
    )
WHERE p.name = 'International Relations'
  AND u.name = 'VIZJA University';


-- ============================================================
-- Sports Business
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'High school diploma',
                                                  'Minimum grade average of 65%',
                                                  'IELTS 6.0 or TOEFL iBT 80',
                                                  'Statement of Purpose (500-750 words)',
                                                  'One academic reference',
                                                  'Demonstrated interest in sports industry',
                                                  'Basic business knowledge preferred'
    )
WHERE p.name = 'Sports Business'
  AND u.name = 'VIZJA University';


-- ============================================================
-- Architecture
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'Bachelor''s degree in Architecture or Engineering',
                                                  'Portfolio of architectural/design work',
                                                  'Minimum GPA of 3.5 (or equivalent)',
                                                  'IELTS 7.0 or TOEFL iBT 95',
                                                  'Statement of Purpose (800-1000 words)',
                                                  'Two academic references',
                                                  'Proficiency in CAD software required'
    )
WHERE p.name = 'Architecture'
  AND u.name = 'VIZJA University';


-- ============================================================
-- Pharmacy
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'High school diploma with strong science background',
                                                  'Excellent grades in Chemistry, Biology, and Mathematics',
                                                  'IELTS 7.5 or TOEFL iBT 100',
                                                  'Statement of Purpose (1000 words)',
                                                  'Two academic references',
                                                  'Medical fitness certificate',
                                                  'Interview may be required'
    )
WHERE p.name = 'Pharmacy'
  AND u.name = 'VIZJA University';


-- ============================================================
-- Marketing Business Management
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'Bachelor''s degree in Marketing, Business, or related field',
                                                  'Minimum GPA of 3.0 (or equivalent)',
                                                  'IELTS 6.5 or TOEFL iBT 90',
                                                  'Statement of Purpose (600-900 words)',
                                                  'Two professional or academic references',
                                                  'CV with marketing or business experience',
                                                  'Portfolio of marketing projects preferred'
    )
WHERE p.name = 'Marketing Business Management'
  AND u.name = 'VIZJA University';


-- ============================================================
-- English Philology
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'High school diploma',
                                                  'Advanced English proficiency (C1 level)',
                                                  'IELTS 7.5 or TOEFL iBT 100',
                                                  'Statement of Purpose (600-800 words)',
                                                  'One academic reference',
                                                  'English language proficiency interview',
                                                  'Literature or language studies background preferred'
    )
WHERE p.name = 'English Philology'
  AND u.name = 'VIZJA University';


-- ============================================================
-- Real Estate and Investments
-- ============================================================
INSERT INTO program_requirements (program_id, requirement_id)
SELECT p.id, r.id
FROM programs p
         JOIN universities u ON p.university_id = u.id
         JOIN requirements r ON r.description IN (
                                                  'High school diploma',
                                                  'Minimum grade average of 70%',
                                                  'IELTS 6.5 or TOEFL iBT 85',
                                                  'Statement of Purpose (500-750 words)',
                                                  'One academic reference',
                                                  'Basic mathematics and economics knowledge',
                                                  'Interest in property and investment markets'
    )
WHERE p.name = 'Real Estate and Investments'
  AND u.name = 'VIZJA University';



