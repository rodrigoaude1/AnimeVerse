-- Script para criar todas as tabelas do AnimeVerse no PostgreSQL

-- Tabela de usuários
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    accepts_terms BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de animes
CREATE TABLE animes (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    cover_image_url VARCHAR(500),
    banner_image_url VARCHAR(500),
    release_date DATE,
    status VARCHAR(50) DEFAULT 'ONGOING',
    average_rating DECIMAL(3,2) DEFAULT 0.0,
    total_episodes INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de mangás
CREATE TABLE mangas (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    cover_image_url VARCHAR(500),
    banner_image_url VARCHAR(500),
    release_date DATE,
    status VARCHAR(50) DEFAULT 'ONGOING',
    average_rating DECIMAL(3,2) DEFAULT 0.0,
    total_chapters INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de gêneros
CREATE TABLE genres (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de relacionamento anime-gênero
CREATE TABLE anime_genres (
    anime_id BIGINT REFERENCES animes(id) ON DELETE CASCADE,
    genre_id BIGINT REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY (anime_id, genre_id)
);

-- Tabela de relacionamento mangá-gênero
CREATE TABLE manga_genres (
    manga_id BIGINT REFERENCES mangas(id) ON DELETE CASCADE,
    genre_id BIGINT REFERENCES genres(id) ON DELETE CASCADE,
    PRIMARY KEY (manga_id, genre_id)
);

-- Tabela de episódios
CREATE TABLE episodes (
    id BIGSERIAL PRIMARY KEY,
    anime_id BIGINT REFERENCES animes(id) ON DELETE CASCADE,
    episode_number INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    release_date DATE,
    duration_minutes INTEGER,
    average_rating DECIMAL(3,2) DEFAULT 0.0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(anime_id, episode_number)
);

-- Tabela de capítulos
CREATE TABLE chapters (
    id BIGSERIAL PRIMARY KEY,
    manga_id BIGINT REFERENCES mangas(id) ON DELETE CASCADE,
    chapter_number INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    release_date DATE,
    page_count INTEGER,
    average_rating DECIMAL(3,2) DEFAULT 0.0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(manga_id, chapter_number)
);

-- Tabela de avaliações de animes
CREATE TABLE anime_ratings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    anime_id BIGINT REFERENCES animes(id) ON DELETE CASCADE,
    rating INTEGER CHECK (rating >= 0 AND rating <= 5),
    review TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, anime_id)
);

-- Tabela de avaliações de mangás
CREATE TABLE manga_ratings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    manga_id BIGINT REFERENCES mangas(id) ON DELETE CASCADE,
    rating INTEGER CHECK (rating >= 0 AND rating <= 5),
    review TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, manga_id)
);

-- Tabela de avaliações de episódios
CREATE TABLE episode_ratings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    episode_id BIGINT REFERENCES episodes(id) ON DELETE CASCADE,
    rating INTEGER CHECK (rating >= 0 AND rating <= 5),
    review TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, episode_id)
);

-- Tabela de avaliações de capítulos
CREATE TABLE chapter_ratings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    chapter_id BIGINT REFERENCES chapters(id) ON DELETE CASCADE,
    rating INTEGER CHECK (rating >= 0 AND rating <= 5),
    review TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, chapter_id)
);

-- Tabela de favoritos
CREATE TABLE favorites (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    content_type VARCHAR(10) CHECK (content_type IN ('ANIME', 'MANGA')),
    content_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, content_type, content_id)
);

-- Tabela de listas de usuários
CREATE TABLE user_lists (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    is_public BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de itens das listas
CREATE TABLE user_list_items (
    id BIGSERIAL PRIMARY KEY,
    list_id BIGINT REFERENCES user_lists(id) ON DELETE CASCADE,
    content_type VARCHAR(10) CHECK (content_type IN ('ANIME', 'MANGA')),
    content_id BIGINT NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(list_id, content_type, content_id)
);

-- Tabela de observações
CREATE TABLE observations (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    content_type VARCHAR(10) CHECK (content_type IN ('ANIME', 'MANGA')),
    content_id BIGINT NOT NULL,
    observation TEXT NOT NULL,
    is_public BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Inserir gêneros padrão
INSERT INTO genres (name, description) VALUES
('Ação', 'Animes e mangás com muita ação e aventura'),
('Aventura', 'Histórias de aventura e exploração'),
('Drama', 'Narrativas dramáticas e emocionais'),
('Romance', 'Histórias de amor e relacionamentos'),
('Comédia', 'Conteúdo humorístico e divertido'),
('Fantasia', 'Mundos fantásticos e mágicos'),
('Ficção Científica', 'Histórias futuristas e tecnológicas'),
('Terror', 'Conteúdo de horror e suspense'),
('Slice of Life', 'Histórias do cotidiano'),
('Shonen', 'Direcionado ao público jovem masculino'),
('Shojo', 'Direcionado ao público jovem feminino'),
('Seinen', 'Direcionado ao público adulto masculino'),
('Josei', 'Direcionado ao público adulto feminino'),
('Mecha', 'Robôs gigantes e tecnologia'),
('Esportes', 'Competições esportivas'),
('Sobrenatural', 'Elementos sobrenaturais e místicos');

-- Criar índices para melhor performance
CREATE INDEX idx_animes_title ON animes(title);
CREATE INDEX idx_mangas_title ON mangas(title);
CREATE INDEX idx_episodes_anime_id ON episodes(anime_id);
CREATE INDEX idx_chapters_manga_id ON chapters(manga_id);
CREATE INDEX idx_anime_ratings_anime_id ON anime_ratings(anime_id);
CREATE INDEX idx_manga_ratings_manga_id ON manga_ratings(manga_id);
CREATE INDEX idx_favorites_user_id ON favorites(user_id);
CREATE INDEX idx_user_lists_user_id ON user_lists(user_id);
CREATE INDEX idx_observations_user_id ON observations(user_id);

